package com.geofence.geofencing_backend.repositories;


import com.geofence.geofencing_backend.entities.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Event Log Repository
 * Provides CRUD operations for EventLog entities using Spring Data JPA.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */
@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Long> {
}
