package com.geofence.geofencing_backend.services;

/* FirebaseService
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 02-05-2024
 * Last Modified on: 02-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 *
 * Service for
 */

import com.geofence.geofencing_backend.repositories.CurrentLocationRepository;
import com.geofence.geofencing_backend.entities.CurrentLocation;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FirebaseService {

    @Autowired
    private FirebaseApp firebaseApp;

    @Autowired
    private CurrentLocationRepository currentLocationRepository;

    /*
     * Fetches the Firestore NoSQL database and saves the retrieved items to the Backend Database
     * TODO: 1. Handle duplicate data; do not save data that already exists on local DB
     */
    public void fetchAndSaveLocationData() {

        // Create a GeometryFactory using the CoordinateSequenceFactory
        GeometryFactory geometryFactory = new GeometryFactory(new PackedCoordinateSequenceFactory());

        // Get a reference to the Firebase Firestore database
        Firestore firestore = FirestoreClient.getFirestore(firebaseApp);

        // Reference to the "locations" collection in the Firebase Firestore database
        CollectionReference locationsCollectionReference = firestore.collection("Current Location");

        // Add a snapshot listener for changes to the "locations" collection
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
                GeoPoint position = doc.getGeoPoint("position"); //GeoPoint contains foelds lat & longitude
                double latitude = position.getLatitude();
                double longitude = position.getLongitude();

                // Create a Coordinate object using the latitude and longitude
                Coordinate coordinate = new Coordinate(latitude, longitude);

                // Create a CoordinateSequence using the Coordinate
                CoordinateSequence coordinates = new CoordinateArraySequence(new Coordinate[]{coordinate});

                // Create a Point using the GeometryFactory and CoordinateSequence
                Point point = new Point(coordinates, geometryFactory);


                // Create a new CurrentLocation entity and save it using the currentLocationRepository
                CurrentLocation currentLocation = new CurrentLocation(time, point);
                currentLocationRepository.save(currentLocation);
            }
        });
    }

}
