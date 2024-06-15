package com.geofence.geofencing_backend.repositories;

import com.geofence.geofencing_backend.entities.Route;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Route Repository
 * Provides CRUD operations for Route entities using Spring Data JPA.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 25-04-2024
 * Last Modified on: 14-06-2024
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

    /**
     * Retrieves the number of points in a route's LineString.
     *
     * @param routeId The ID of the route to query.
     * @return The number of points in the route.
     */
    @Query(value = "SELECT ST_NumPoints(points) FROM Route WHERE id = :routeId", nativeQuery = true)
    int getNumberOfPointsInRoute(@Param("routeId") Long routeId);

    /**
     * Creates or initializes a new route's LineString if it has zero points using a spatial query.
     *
     * @param routeId  the ID of the route to update
     * @param latitude  the latitude of the new point
     * @param longitude  the longitude of the new point
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Route SET points = ST_SetSRID(ST_MakeLine(ST_MakePoint(:longitude, :latitude), ST_MakePoint(:longitude, :latitude)), 4326) WHERE id = :routeId", nativeQuery = true)
    void initializeRoutePoints(@Param("routeId") Long routeId, @Param("longitude") double longitude, @Param("latitude") double latitude);


    /**
     * Adds a point to the route's LineString using a spatial query.
     *
     * @param routeId  the ID of the route to update
     * @param latitude  the latitude of the new point
     * @param longitude  the longitude of the new point
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Route SET points = ST_AddPoint(points, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) WHERE id = :routeId", nativeQuery = true)
    void addPointToRoute(@Param("routeId") Long routeId, @Param("longitude") double longitude, @Param("latitude") double latitude);

}
