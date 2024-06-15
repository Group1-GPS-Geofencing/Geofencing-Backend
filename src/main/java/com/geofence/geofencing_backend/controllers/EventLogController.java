package com.geofence.geofencing_backend.controllers;

import com.geofence.geofencing_backend.entities.EventLog;
import com.geofence.geofencing_backend.services.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Log Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@RestController
@RequestMapping(path = "/api/v1/logs", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventLogController {


    @Autowired
    private final EventLogService eventLogService;

    /**
     * Constructor for EventLogController.
     *
     * @param eventLogService the service to manage event logs
     */
    public EventLogController(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }



    /**
     * Handles HTTP POST requests to create a new event log.
     * <p>
     * Example request:
     * POST <a href="http://localhost:8080/api/v1/logs">...</a>
     * {
     * "time": "2024-04-25T12:00:00Z",
     * "message": "Hello Mom",
     * "fence": null
     * }
     *
     * @param eventLog the event log to be created
     * @return the created event log
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventLog> createLocation(@RequestBody EventLog eventLog) {
        EventLog createdEventLog = eventLogService.createEventLog(eventLog);
        return new ResponseEntity<>(createdEventLog, HttpStatus.CREATED);
    }


    /**
     * Handles HTTP GET requests to retrieve an event log by its ID.
     * <p>
     * Example request:
     * GET <a href="http://localhost:8080/api/v1/logs/1">...</a>
     *
     * @param id the ID of the event log to retrieve
     * @return the event log with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventLog> getEventLogById(@PathVariable Long id) {
        EventLog eventLog = eventLogService.getEventLogById(id);
        if (eventLog != null) {
            return ResponseEntity.ok(eventLog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    /**
     * Handles HTTP GET requests to retrieve all event logs.
     * <p>
     * Example request:
     * GET <a href="http://localhost:8080/api/v1/logs">...</a>
     *
     * @return a list of all event logs, or 204 if none exist
     */
    @GetMapping
    public ResponseEntity<List<EventLog>> getAllEventLogs() {
        List<EventLog> eventLogs = eventLogService.getAllLogs();
        if (!eventLogs.isEmpty()) {
            return ResponseEntity.ok(eventLogs);
        } else {
            return ResponseEntity.noContent().build();
        }
    }



    /**
     * Handles HTTP DELETE requests to delete an event log by its ID.
     * <p>
     * Example request:
     * DELETE <a href="http://localhost:8080/api/v1/logs/1">...</a>
     *
     * @param id the ID of the event log to delete
     * @return a response entity with no content
     */
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteEventLog(@PathVariable Long id) {
        eventLogService.deleteEventLog(id);
        return ResponseEntity.noContent().build();
    }

}
