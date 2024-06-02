package com.geofence.geofencing_backend.controllers;

/*
 * Route Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 25-04-2024
 * Last Modified on: 25-04-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.Route;
import com.geofence.geofencing_backend.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/route", produces = MediaType.APPLICATION_JSON_VALUE)
public class RouteController {

    @Autowired
    private final RouteService routeService;

    //args constructor
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    /*
        HTTP POST request handler

        i.e., http://localhost:8080/api/v1/route
        Receives the following parameters to create a route instance
        {
            "name": "mwambo to Chimphamba",
            "start_time": "2024-04-25T12:00:00Z",
            "end_time": "2024-04-25T12:00:00Z",
            "points": {
                "type": "LineString",
                "coordinates": [
                    [
                        35.33702,
                        -15.385777
                    ],
                    [
                        35.337072,
                        -15.386059
                    ],
                    [
                        35.336013,
                        -15.387057
                    ],
                    [
                        35.336025,
                        -15.387243
                    ],
                    [
                        35.336056,
                        -15.387366
                    ],
                    [
                        35.336066,
                        -15.387407
                    ],
                    [
                        35.33644,
                        -15.387689
                    ],
                    [
                        35.336849,
                        -15.388097
                    ]
                ]
            }
        }
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route createdRoute = routeService.createRoute(route);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    /*
        HTTP GET/id request handler
        Receives the id parameter to return a specific route instance if it exists
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


    /*
        HTTP GET request handler
        Receives no parameters to return a list of all route instances
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

    /*
        HTTP PUT/id request handler
        Receives the id parameters to update a fence instance by id if it exists

        i.e., http://localhost:8080/api/v1/route/2

        Receives the following parameters to update an existing fence instance
        {
        }
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        route.setId(id);
        Route updatedRoute = routeService.updateRoute(route);
        return ResponseEntity.ok(updatedRoute);
    }


    /*
        HTTP DELETE/id request handler
        Receives the id parameter to delete a route  instance by id if it exists

        i.e., http://localhost:8080/api/v1/route/2
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
