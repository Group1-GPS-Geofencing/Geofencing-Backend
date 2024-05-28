package com.geofence.geofencing_backend.repositories;

/*
 * Current Location Repository
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 28-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.Location;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByTimeAndPoint(Timestamp time, Point point);
}
