package com.geofence.geofencing_backend.services;

//TODO: modify class to meet updates of Location Entity

/*
 * Location Service
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 05-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.Location;
import com.geofence.geofencing_backend.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    //Method to create a location and save it in the database
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    //return the currentLocation by querying using ID
    public Location getCurrentLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    //return the List of currentLocations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    //update location
    //of course, since there will only be one entry of the current location,
    // we'll keep on updating it instead of creating multiple entries
    public Location updateCurrentLocation(Location location) {

        // Check if the location exists
        if (location.getId() == null || !locationRepository.existsById(location.getId())) {
            throw new IllegalArgumentException("Location not found");
        }
        return locationRepository.save(location);
    }

    //deletes current location by ID
    public void deleteCurrentLocation(Long id) {
        locationRepository.deleteById(id);
    }

}
