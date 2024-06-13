package com.geofence.geofencing_backend.controllers;

import com.geofence.geofencing_backend.entities.Route;
import com.geofence.geofencing_backend.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Route Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 25-04-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@RestController
@RequestMapping(path = "/api/v1/route", produces = MediaType.APPLICATION_JSON_VALUE)
public class RouteController {

    @Autowired
    private final RouteService routeService;

    /**
     * Constructor for RouteController.
     *
     * @param routeService the service to manage routes
     */
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    /**
     * Handles HTTP POST requests to create a new route.
     * <p>
     * Example request:
     * POST <a href="http://localhost:8080/api/v1/route">...</a>
     * {
     * "name": "mwambo to Chimphamba",
     * "start_time": "2024-04-25T12:00:00Z",
     * "end_time": "2024-04-25T12:00:00Z",
     * "points": {
     * "type": "LineString",
     * "coordinates": [
     * [35.33702, -15.385777],
     * [35.337072, -15.386059],
     * [35.336013, -15.387057],
     * [35.336025, -15.387243],
     * [35.336056, -15.387366],
     * [35.336066, -15.387407],
     * [35.33644, -15.387689],
     * [35.336849, -15.388097]
     * ]
     * }
     * }
     *
     * @param route the route to be created
     * @return the created route
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route createdRoute = routeService.createRoute(route);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    /**
     * Handles HTTP GET requests to retrieve a route by its ID.
     *
     * @param id the ID of the route to retrieve
     * @return the route with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        Route route = routeService.getRouteByID(id);
        if (route != null) {
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Handles HTTP GET requests to retrieve all routes.
     *
     * @return a list of all routes, or 204 if none exist
     */
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        if (!routes.isEmpty()) {
            return ResponseEntity.ok(routes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    /**
     * Handles HTTP PUT requests to update a route by its ID.
     * <p>
     * Example request:
     * PUT <a href="http://localhost:8080/api/v1/route/2">...</a>
     * {
     * "name": "Updated route name",
     * "start_time": "2024-04-25T12:00:00Z",
     * "end_time": "2024-04-25T12:00:00Z",
     * "points": {
     * "type": "LineString",
     * "coordinates": [
     * [35.33702, -15.385777],
     * [35.337072, -15.386059],
     * [35.336013, -15.387057]
     * ]
     * }
     * }
     *
     * @param id    the ID of the route to update
     * @param route the updated route
     * @return the updated route
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        route.setId(id);
        Route updatedRoute = routeService.updateRoute(route);
        return ResponseEntity.ok(updatedRoute);
    }



    /**
     * Handles HTTP DELETE requests to delete a route by its ID.
     * <p>
     * Example request:
     * DELETE <a href="http://localhost:8080/api/v1/route/2">...</a>
     *
     * @param id the ID of the route to delete
     * @return a response entity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
