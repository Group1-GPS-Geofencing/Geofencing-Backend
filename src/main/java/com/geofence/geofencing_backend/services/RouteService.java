package com.geofence.geofencing_backend.services;

/*
 * Route Service
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 25-04-2024
 * Last Modified on: 28-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.*;
import com.geofence.geofencing_backend.repositories.RouteRepository;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);

    //creates a route and saves it to the repository
    @Transactional
    public Route createRoute(Route route){
        return routeRepository.save(route);
    }

    //returns a route after querying by ID
    public Route getRouteByID(Long id){
        return routeRepository.findById(id).orElse(null);
    }

    //returns all routes
    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }

    //update route
    @Transactional
    public Route updateRoute(Route route){
        // Check if the route exists
        if (route.getId() == null || !routeRepository.existsById(route.getId())) {
            throw new IllegalArgumentException("Route not found");
        }
        return routeRepository.save(route);
    }

    //delete route
    @Transactional
    public void deleteRoute(Long id){
        routeRepository.deleteById(id);
    }


    /*
     * Finds or creates a route for the given timestamp
     */
    @Transactional
    public Route findOrCreateRouteForTimestamp(Timestamp timestamp) {
        // Extract the date from the timestamp
        LocalDate date = timestamp.toLocalDateTime().toLocalDate();

        // Convert LocalDate to Timestamp
        Timestamp timestampDate = Timestamp.valueOf(date.atStartOfDay());

        // Check if a route exists for the given date
        Route existingRoute = routeRepository.findByDate(timestampDate);

        if (existingRoute != null) {
            // If a route exists, return it
            return existingRoute;
        } else {
            // If no route exists, create a new route
            Route newRoute = new Route();
            newRoute.setDate(timestampDate);
            newRoute.setName("Route for " + date);

            // Create an empty LineString
            GeometryFactory geometryFactory = new GeometryFactory();
            newRoute.setPoints(geometryFactory.createLineString(new Coordinate[0]));
            return routeRepository.save(newRoute);
        }
    }


    /*
    *   Updates the given Route by appending a point (pair of coordinates)
    */
    @Transactional
    public void updateRouteWithLocationCoordinates(Route route, Point newPoint) {
        logger.info("Starting update of route with new point: " + newPoint);

        // Get the existing LineString from the route entity
        LineString existingLineString = route.getPoints();
        if (existingLineString == null) {
            logger.warn("Existing LineString is null, initializing with empty coordinates.");
            existingLineString = new GeometryFactory().createLineString(new Coordinate[0]);
        }

        // Append the new point to the existing LineString
        LineString updatedLineString = appendCoordinate(existingLineString, newPoint);
        logger.info("Updated LineString: " + updatedLineString);

        // Update the route entity with the updated LineString
        route.setPoints(updatedLineString);

        logger.info("Route updated with new Coordinates: " + newPoint);
        // route.addLocation(new Location(new Timestamp(System.currentTimeMillis()), newPoint));
        // Save the updated route entity
        Route updatedRoute = routeRepository.save(route);
        logger.info("Route successfully updated and saved: " + updatedRoute);
    }

    // Helper method to append coordinates to a LineString
    private LineString appendCoordinate(LineString lineString, Point newPoint) {
        // Extract the coordinates from the existing LineString
        Coordinate[] existingCoordinates = lineString.getCoordinates();

        // Create a new array to hold the updated coordinates
        Coordinate[] updatedCoordinates = new Coordinate[existingCoordinates.length + 1];

        // Copy the existing coordinates to the updated array
        System.arraycopy(existingCoordinates, 0, updatedCoordinates, 0, existingCoordinates.length);

        // Append the new coordinate to the end of the array
        updatedCoordinates[existingCoordinates.length] = newPoint.getCoordinate();

        // Create a new LineString with the updated coordinates
        GeometryFactory geometryFactory = new GeometryFactory();
        LineString updatedLineString = geometryFactory.createLineString(updatedCoordinates);
        logger.info("Appending coordinate: " + newPoint.getCoordinate() + " to LineString. Updated LineString: " + updatedLineString);
        return updatedLineString;
    }
}
