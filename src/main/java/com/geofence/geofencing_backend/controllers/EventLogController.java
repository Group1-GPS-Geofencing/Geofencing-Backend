package com.geofence.geofencing_backend.controllers;

/*
 * Log Controller
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 31-03-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.EventLog;
import com.geofence.geofencing_backend.services.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/logs")
public class EventLogController {


    @Autowired
    private final EventLogService eventLogService;

    //arg constructor
    public EventLogController(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }


    /*
        HTTP POST request handler
        Receives the following parameters to create a currentLocation instance
        {
            "date": null,
            "time": null,
            "message": null,
            "fence": null
        }
     */
    @PostMapping
    public ResponseEntity<EventLog> createLocation(@RequestBody EventLog eventLog) {
        EventLog createdEventLog = eventLogService.createEventLog(eventLog);
        return new ResponseEntity<>(createdEventLog, HttpStatus.CREATED);
    }


    /*
        HTTP GET/id request handler
        Receives the id parameter to return a specific eventLog instance if it exists
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


    /*
        HTTP GET request handler
        Receives no parameters to return a list of eventLogs instances
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


    /*
        HTTP PUT/id request handler
        Receives the id parameters to update an eventLog instance by id if it exists
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventLog> updateEventLog(@PathVariable Long id, @RequestBody EventLog eventLog) {
        eventLog.setId(id);
        EventLog updatedEventLog = eventLogService.updateEventLog(eventLog);
        return ResponseEntity.ok(updatedEventLog);
    }


    /*
        HTTP DELETE/id request handler
        Receives the id parameter to delete an eventLog  instance by id if it exists
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventLog(@PathVariable Long id) {
        eventLogService.deleteEventLog(id);
        return ResponseEntity.noContent().build();
    }

}
