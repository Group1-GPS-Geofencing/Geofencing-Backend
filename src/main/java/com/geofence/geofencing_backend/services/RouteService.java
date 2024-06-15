package com.geofence.geofencing_backend.services;

import com.geofence.geofencing_backend.entities.*;
import com.geofence.geofencing_backend.entities.Location;
import com.geofence.geofencing_backend.repositories.RouteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * Route Service
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 25-04-2024
 * Last Modified on: 14-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);

    /**
     * Creates a new route and saves it to the repository.
     *
     * @param route the route to be created
     * @return the created route
     */
    @Transactional
    public Route createRoute(Route route){
        return routeRepository.save(route);
    }

    /**
     * Returns a route by querying with its ID.
     *
     * @param id the ID of the route
     * @return the route with the specified ID, or null if not found
     */
    public Route getRouteByID(Long id){
        return routeRepository.findById(id).orElse(null);
    }

    /**
     * Returns all routes.
     *
     * @return a list of all routes
     */
    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }

    /**
     * Updates an existing route.
     *
     * @param route the route to be updated
     * @return the updated route
     */
    @Transactional
    public Route updateRoute(Route route){
        // Check if the route exists
        if (route.getId() == null || !routeRepository.existsById(route.getId())) {
            throw new IllegalArgumentException("Route not found");
        }
        return routeRepository.save(route);
    }

    /**
     * Deletes a route by its ID.
     *
     * @param id the ID of the route to be deleted
     */
    @Transactional
    public void deleteRoute(Long id){
        routeRepository.deleteById(id);
    }

    /**
     * Finds or creates a route for the given timestamp.
     *
     * @param timestamp the timestamp to find or create a route for
     * @return the existing or newly created route
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

    /**
     * Updates the route with the given location coordinates.
     *
     * @param route  the route to update
     * @param point  the point to add to the route's LineString
     */
    public void updateRouteWithLocationCoordinates(Route route, Point point) {
        Long routeId = route.getId();
        double latitude = point.getX();
        double longitude = point.getY();

        if (routeRepository.getNumberOfPointsInRoute(routeId) == 0) {
            // Initialize the route with the same point twice since we can't initialize a line string with less than 2 points
            logger.info("0 points in linestring --initialize route");
            routeRepository.initializeRoutePoints(routeId, longitude, latitude);

        } else {
            routeRepository.addPointToRoute(routeId, longitude, latitude);
            logger.info("linestring not empty -- added point");
        }
    }

}
