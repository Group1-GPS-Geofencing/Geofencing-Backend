package com.geofence.geofencing_backend.services;

//TODO: modify class to meet updates of Location Entity


import com.geofence.geofencing_backend.entities.Location;
import com.geofence.geofencing_backend.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Location Service
 * Handles CRUD operations for Location entities.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 05-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    /**
     * Creates a new location.
     *
     * @param location The Location entity to be created.
     * @return The created Location entity.
     */
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    /**
     * Retrieves a location by its ID.
     *
     * @param id The ID of the Location entity to retrieve.
     * @return The Location entity if found, otherwise null.
     */
    public Location getCurrentLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all locations.
     *
     * @return A list of all Location entities.
     */
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    /**
     * Updates an existing location.
     *
     * @param location The updated Location entity.
     * @return The updated Location entity.
     * @throws IllegalArgumentException If the Location entity with the given ID does not exist.
     */
    public Location updateCurrentLocation(Location location) {

        if (location.getId() == null || !locationRepository.existsById(location.getId())) {
            throw new IllegalArgumentException("Location not found");
        }
        return locationRepository.save(location);
    }

    /**
     * Deletes a location by its ID.
     *
     * @param id The ID of the Location entity to delete.
     */
    public void deleteCurrentLocation(Long id) {
        locationRepository.deleteById(id);
    }

}
