package com.geofence.geofencing_backend.services;

import com.geofence.geofencing_backend.entities.EventLog;
import com.geofence.geofencing_backend.repositories.EventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Log Service
 * Handles CRUD operations for EventLog entities.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Service
public class EventLogService {

    @Autowired
    private EventLogRepository eventLogRepository;

    /**
     * Creates a new event log.
     *
     * @param eventLog The EventLog entity to be created.
     * @return The created EventLog entity.
     */
    public EventLog createEventLog(EventLog eventLog) {
        return eventLogRepository.save(eventLog);
    }

    /**
     * Retrieves an event log by its ID.
     *
     * @param id The ID of the EventLog entity to retrieve.
     * @return The EventLog entity if found, otherwise null.
     */
    public EventLog getEventLogById(Long id){
        return eventLogRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all event logs.
     *
     * @return A list of all EventLog entities.
     */
    public List<EventLog> getAllLogs() {
        return eventLogRepository.findAll();
    }

    /**
     * Updates an existing event log.
     *
     * @param eventLog The updated EventLog entity.
     * @return The updated EventLog entity.
     * @throws IllegalArgumentException If the EventLog entity with the given ID does not exist.
     */
    public EventLog updateEventLog(EventLog eventLog) {
        if (eventLog.getId() == null || !eventLogRepository.existsById(eventLog.getId())) {
            throw new IllegalArgumentException("Log not found");
        }
        return eventLogRepository.save(eventLog);
    }

    /**
     * Deletes an event log by its ID.
     *
     * @param id The ID of the EventLog entity to delete.
     */
    public void deleteEventLog(Long id) {
        eventLogRepository.deleteById(id);
    }

}
