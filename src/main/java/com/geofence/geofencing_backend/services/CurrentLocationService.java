package com.geofence.geofencing_backend.services;

/*
 * Current Location Service
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 28-03-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.CurrentLocation;
import com.geofence.geofencing_backend.repositories.CurrentLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrentLocationService {

    @Autowired
    private CurrentLocationRepository currentLocationRepository;

    //Method to create a currentLocation and save it in the database
    public CurrentLocation createLocation(CurrentLocation currentLocation) {
        return currentLocationRepository.save(currentLocation);
    }

    //return the currentLocation by querying using ID
    public CurrentLocation getCurrentLocationById(Long id) {
        return currentLocationRepository.findById(id).orElse(null);
    }

    //return the List of currentLocations
    public List<CurrentLocation> getAllLocations() {
        return currentLocationRepository.findAll();
    }

    //update currentLocation
    //of course, since there will only be one entry of the current location,
    // we'll keep on updating it instead of creating multiple entries
    public CurrentLocation updateCurrentLocation(CurrentLocation currentLocation) {

        // Check if the currentLocation exists
        if (currentLocation.getId() == null || !currentLocationRepository.existsById(currentLocation.getId())) {
            throw new IllegalArgumentException("Location not found");
        }
        return currentLocationRepository.save(currentLocation);
    }

}
