package com.geofence.geofencing_backend.repositories;

import com.geofence.geofencing_backend.entities.Location;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


/**
 * Current Location Repository
 * Provides CRUD operations for Location entities using Spring Data JPA.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    /**
     * Checks if a location exists with the given timestamp and point.
     *
     * @param time  The timestamp of the location.
     * @param point The point (coordinates) of the location.
     * @return true if a location exists with the given timestamp and point, otherwise false.
     */
    boolean existsByTimeAndPoint(Timestamp time, Point point);

    /**
     * Retrieves the most recent location entry.
     *
     * @return The most recent location entry.
     */
    Location findTopByOrderByTimeDesc();
}
