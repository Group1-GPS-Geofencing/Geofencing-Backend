package com.geofence.geofencing_backend.services;

/*
 * Fence Monitoring Service; for detecting fence entry or exit
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 16-05-2024
 * Last Modified on: 29-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.EventLog;
import com.geofence.geofencing_backend.entities.Fence;
import com.geofence.geofencing_backend.entities.Location;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FenceMonitoringService {

    //logger
    private final Logger logger = LoggerFactory.getLogger(FenceMonitoringService.class);

    @Autowired
    private FenceService fenceService;

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private TwilioSMSService twilioSMSService;

    // Helper method to monitor fence entry/exit based on location
    // Focus is only on exit
    public void monitorFenceEntryExit(Location location) {
        // get active fence from the database
        Fence activeFence = fenceService.getActiveFence();

        if (activeFence == null) {
            logger.warn("No active fence found.");
            return;
        }

        logger.info("Active fences: " + activeFence.toString());
        // Check if the location falls within the fence boundary
        if (isInsideFence(location.getPoint(), activeFence.getBoundary())) {
            // User entered the fence, trigger entry logic
            logger.info("Fence not exited");
            createAndSaveEventLog(activeFence, "Entry");
        } else {
            // User exited the fence, trigger exit logic
            logger.info("Fence exited");
            createAndSaveEventLog(activeFence, "Exit");
        }

    }

    // Helper method to check if a point is inside a fence boundary/ polygon
    private boolean isInsideFence(Point point, Polygon boundary) {
        return boundary.contains(point);
    }

    // Helper method to create and save event log
    private void createAndSaveEventLog(Fence fence, String message) {
        EventLog eventLog = new EventLog(new Timestamp(System.currentTimeMillis()), message, fence);
        eventLogService.createEventLog(eventLog);
        twilioSMSService.sendSMS("Fence: " + fence.getName() + ", " + message);
    }
}
