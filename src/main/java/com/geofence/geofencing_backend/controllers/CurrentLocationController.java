package com.geofence.geofencing_backend.controllers;

/*
 * CurrentLocation Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 31-03-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.CurrentLocation;
import com.geofence.geofencing_backend.services.CurrentLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/location")
public class CurrentLocationController {


    @Autowired
    private final CurrentLocationService locationService;

    //arg constructor
    public CurrentLocationController(CurrentLocationService locationService) {
        this.locationService = locationService;
    }


    /*
        HTTP POST request handler
        Receives the following parameters to create a currentLocation instance
        {"longitude": "3.111",
        "latitude": "3.111"}
     */
    @PostMapping
    public ResponseEntity<CurrentLocation> createLocation(@RequestBody CurrentLocation location) {
        CurrentLocation createdLocation = locationService.createLocation(location);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }


    /*
        HTTP GET/id request handler
        Receives the id parameter to return a specific currentLocation instance if it exists
     */
    @GetMapping("/{id}")
    public ResponseEntity<CurrentLocation> getLocationById(@PathVariable Long id) {
        CurrentLocation location = locationService.getCurrentLocationById(id);
        if (location != null) {
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /*
        HTTP GET request handler
        Receives no parameters to return a list of currentLocation instances
     */
    @GetMapping
    public ResponseEntity<List<CurrentLocation>> getAllLocations() {
        List<CurrentLocation> locations = locationService.getAllLocations();
        if (!locations.isEmpty()) {
            return ResponseEntity.ok(locations);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    /*
        HTTP PUT/id request handler
        Receives the id parameters to update a currentLocation instance by id if it exists
     */
    @PutMapping("/{id}")
    public ResponseEntity<CurrentLocation> updateLocation(@PathVariable Long id, @RequestBody CurrentLocation location) {
        location.setId(id);
        CurrentLocation updatedLocation = locationService.updateCurrentLocation(location);
        return ResponseEntity.ok(updatedLocation);
    }


    /*
        HTTP DELETE/id request handler
        Receives the id parameter to delete a currentLocation instance by id if it exists
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteCurrentLocation(id);
        return ResponseEntity.noContent().build();
    }

}
