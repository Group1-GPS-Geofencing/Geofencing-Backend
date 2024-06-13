package com.geofence.geofencing_backend.controllers;

import com.geofence.geofencing_backend.entities.Location;
import com.geofence.geofencing_backend.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Location Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */


@RestController
@RequestMapping(path = "/api/v1/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {


    @Autowired
    private final LocationService locationService;

    /**
     * Constructor for LocationController.
     *
     * @param locationService the service to manage locations
     */
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }




    /**
     * Handles HTTP POST requests to create a new location.
     * <p>
     * Example request:
     * POST <a href="http://localhost:8080/api/v1/location">...</a>
     * {
     * "time": "2024-04-25T12:00:00Z",
     * "point": {
     * "type": "Point",
     * "coordinates": [
     * -10.0, 20.0
     * ]
     * }
     * }
     *
     * @param location the location to be created
     * @return the created location
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }



    /**
     * Handles HTTP GET requests to retrieve a location by its ID.
     * <p>
     * Example request:
     * GET <a href="http://localhost:8080/api/v1/location/2">...</a>
     *
     * @param id the ID of the location to retrieve
     * @return the location with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Location location = locationService.getCurrentLocationById(id);
        if (location != null) {
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Handles HTTP GET requests to retrieve all locations.
     *
     * @return a list of all locations, or 204 if none exist
     */
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        if (!locations.isEmpty()) {
            return ResponseEntity.ok(locations);
        } else {
            return ResponseEntity.noContent().build();
        }
    }




    /**
     * Handles HTTP PUT requests to update a location by its ID.
     * <p>
     * Example request:
     * PUT <a href="http://localhost:8080/api/v1/location/2">...</a>
     * {
     * "time": "2024-04-25T12:00:00Z",
     * "point": {
     * "type": "Point",
     * "coordinates": [
     * -10.0, 20.0
     * ]
     * }
     * }
     *
     * @param id       the ID of the location to update
     * @param location the updated location
     * @return the updated location
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        location.setId(id);
        Location updatedLocation = locationService.updateCurrentLocation(location);
        return ResponseEntity.ok(updatedLocation);
    }



    /**
     * Handles HTTP DELETE requests to delete a location by its ID.
     * <p>
     * Example request:
     * DELETE <a href="http://localhost:8080/api/v1/location/2">...</a>
     *
     * @param id the ID of the location to delete
     * @return a response entity with no content
     */
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteCurrentLocation(id);
        return ResponseEntity.noContent().build();
    }

}
