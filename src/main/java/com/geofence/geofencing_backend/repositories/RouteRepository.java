package com.geofence.geofencing_backend.repositories;

import com.geofence.geofencing_backend.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Route Repository
 * Provides CRUD operations for Route entities using Spring Data JPA.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 25-04-2024
 * Last Modified on: 25-04-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    /**
     * Finds a route by the specified date.
     *
     * @param date The date to search for routes.
     * @return The Route entity if found, otherwise null.
     */
    Route findByDate(Timestamp date);
}
