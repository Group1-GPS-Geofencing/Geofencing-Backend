package com.geofence.geofencing_backend.controllers;

/*
 * Location Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 05-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.Location;
import com.geofence.geofencing_backend.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/location")
public class LocationController {


    @Autowired
    private final LocationService locationService;

    //arg constructor
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    /*
        HTTP POST request handler
        i.e., http://localhost:8080/api/v1/location

        Receives the following parameters to create a location instance

        {
            "time": "2024-04-25T12:00:00Z",
            "point": {
                "type": "Point",
                "coordinates": [
                    -10.0,
                    20.0
                ]
            }
        }
     */
    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }


    /*
        HTTP GET/id request handler
        i.e., http://localhost:8080/api/v1/location/2

        Receives the id parameter to return a specific location instance if it exists
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


    /*
        HTTP GET request handler
        Receives no parameters to return a list of all location instances
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


    /*
        HTTP PUT/id request handler
        i.e., http://localhost:8080/api/v1/location/2

        Receives the id parameters to update a location instance by id if it exists

        {
            "time": "2024-04-25T12:00:00Z",
            "point": {
                "type": "Point",
                "coordinates": [
                    -10.0,
                    20.0
                ]
            }
        }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        location.setId(id);
        Location updatedLocation = locationService.updateCurrentLocation(location);
        return ResponseEntity.ok(updatedLocation);
    }


    /*
        HTTP DELETE/id request handler
        Receives the id parameter to delete a location instance by id if it exists

        i.e., http://localhost:8080/api/v1/location/2
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteCurrentLocation(id);
        return ResponseEntity.noContent().build();
    }

}
