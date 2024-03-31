package com.geofence.geofencing_backend.repositories;

/*
 * Fence Repository
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 28-03-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */


import com.geofence.geofencing_backend.entities.Fence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FenceRepository extends JpaRepository<Fence, Long> {
    Fence findByIsActiveTrue();
}
