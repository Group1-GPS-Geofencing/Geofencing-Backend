package com.geofence.geofencing_backend.repositories;

/*
 * Route Repository
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 25-04-2024
 * Last Modified on: 25-04-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    //finds a route by date
    Route findByDate(Timestamp date);
}
