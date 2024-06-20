package com.geofence.geofencing_backend.services;


import com.geofence.geofencing_backend.entities.Location;
import com.geofence.geofencing_backend.entities.Route;
import com.geofence.geofencing_backend.repositories.LocationRepository;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.*;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * FirebaseService
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 02-05-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 * <p>
 * Service for Retrieving currentLocation Data from Firebase Firestore cloud service
 */

@Service
public class FirebaseService {

    //logger for logging on the console
    private static final Logger logger = LoggerFactory.getLogger(FirebaseService.class);

    @Autowired
    private FirebaseApp firebaseApp;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RouteService routeService;

    @Autowired
    private TwilioSMSService twilioSMSService;

    @Autowired
    private FenceMonitoringService fenceMonitoringService;

    /*
     * Fetches the Firestore NoSQL database and saves the retrieved items to the Backend Database
     */
    @Transactional
    public void fetchAndSaveLocationData() {

        // Create a GeometryFactory using the CoordinateSequenceFactory
        GeometryFactory geometryFactory = new GeometryFactory(new PackedCoordinateSequenceFactory());

        // Get a reference to the Firebase Firestore database
        // Reference to the "Current Location" collection in the Firebase Firestore database
        Firestore firestore = FirestoreClient.getFirestore(firebaseApp);
        CollectionReference locationsCollectionReference = firestore.collection("Current Location");

        // Add a snapshot listener for changes to the "Current Location" collection
        locationsCollectionReference.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                // Handle errors
                return;
            }

            // Iterate through the documents in the snapshot
            assert snapshot != null;
            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                // Extract data from the document
                Timestamp time = doc.getTimestamp("time").toSqlTimestamp();
                GeoPoint position = doc.getGeoPoint("position"); //GeoPoint contains fields lat & longitude
                double latitude = position.getLatitude();
                double longitude = position.getLongitude();

                Coordinate coordinate = new Coordinate(longitude, latitude);

                // Create a CoordinateSequence using the Coordinate
                CoordinateSequence coordinates = new CoordinateArraySequence(new Coordinate[]{coordinate});
                Point point = new Point(coordinates, geometryFactory);


                // Retrieve the last known location from the repository
                Location lastKnownLocation = locationRepository.findTopByOrderByTimeDesc();
                Point lastKnownPoint = (lastKnownLocation != null) ? lastKnownLocation.getPoint() : null;

                // Check if the location already exists or is within  a radius of 50 from the last known location
                if (lastKnownPoint == null || (!locationExists(time, point) && locationDistanceBetweenPoints(point, lastKnownPoint))) {
                    // Find or create the route for the timestamp
                    logger.info("find or create route");
                    Route route = routeService.findOrCreateRouteForTimestamp(time);

                    // Create a new Location entity and associate it with the route
                    Location location = new Location(time, point);
                    location.setRoute(route);
                    logger.info("Saving location: " + location.toString());

                    //save the location
                    locationRepository.save(location);
                    logger.info("Saved location: " + location.toString());

                    routeService.updateRouteWithLocationCoordinates(route, point);
                    fenceMonitoringService.monitorFenceEntryExit(location);

                    logger.info("Saved Location: " + location);
                    logger.info("Associated Route: " + location.getRoute());
                } else {
                    logger.warn("Location not Saved, Already existing Location: ");
                }
            }
        });
    }

    /**
     * Checks if a location with the given timestamp and point already exists in the database.
     *
     * @param time  The timestamp of the location.
     * @param point The point (coordinates) of the location.
     * @return true if the location exists, false otherwise.
     */
    private boolean locationExists(Timestamp time, Point point) {
        return locationRepository.existsByTimeAndPoint(time, point);
    }

    /**
     * Checks if the distance between the current point and the last known point is greater than or equal to 50 meters.
     * I'm not sure with the Math in terms of accuracy
     *
     * @param currentPoint The current point (coordinates).
     * @param lastKnownPoint The last known point (coordinates).
     * @return true if the distance between the points is greater than or equal to 1 meter, false otherwise.
     */
    private boolean locationDistanceBetweenPoints(Point currentPoint, Point lastKnownPoint) {
        // Calculate the distance between the two points
        double distance = DistanceOp.distance(currentPoint, lastKnownPoint);
        // Convert distance to meters (assuming points are in EPSG:4326 - WGS84)
        double distanceInMeters = distance * 111139; // Approximate conversion factor for degrees to meters
        // Return true if the distance is less than or equal to 2 meters, false otherwise
        logger.info("distance: " + distanceInMeters);
        return distanceInMeters >= 50;
    }
}
