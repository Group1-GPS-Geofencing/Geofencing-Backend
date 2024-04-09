package com.geofence.geofencing_backend.services;

/*
 * Log Service
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 27-03-2024
 * Last Modified on: 28-03-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.EventLog;
import com.geofence.geofencing_backend.repositories.EventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventLogService {

    @Autowired
    private EventLogRepository eventLogRepository;

    public EventLog createEventLog(EventLog eventLog) {
        return eventLogRepository.save(eventLog);
    }

    //returns event log
    public EventLog getEventLogById(Long id){
        return eventLogRepository.findById(id).orElse(null);
    }

    //return all logs
    public List<EventLog> getAllLogs() {
        return eventLogRepository.findAll();
    }

    //update Event Log
    public EventLog updateEventLog(EventLog eventLog) {
        if (eventLog.getId() == null || !eventLogRepository.existsById(eventLog.getId())) {
            throw new IllegalArgumentException("Log not found");
        }
        return eventLogRepository.save(eventLog);
    }

    //delete event log
    public void deleteEventLog(Long id) {
        eventLogRepository.deleteById(id);
    }

}
