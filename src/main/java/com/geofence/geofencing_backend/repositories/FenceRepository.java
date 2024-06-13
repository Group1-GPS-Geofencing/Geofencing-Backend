package com.geofence.geofencing_backend.repositories;

import com.geofence.geofencing_backend.entities.Fence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Fence Repository
 * Provides CRUD operations for Fence entities using Spring Data JPA.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Repository
public interface FenceRepository extends JpaRepository<Fence, Long> {

    /**
     * Retrieves the active fence.
     *
     * @return The active Fence entity if found, otherwise null.
     */
    Fence findByIsActiveTrue();
}
