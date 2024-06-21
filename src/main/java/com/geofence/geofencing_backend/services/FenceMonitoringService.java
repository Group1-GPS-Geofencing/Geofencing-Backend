package com.geofence.geofencing_backend.services;

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

/**
 * Fence Monitoring Service; for detecting fence entry or exit
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 16-05-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Service
public class FenceMonitoringService {

    // Logger for logging fence monitoring activities
    private final Logger logger = LoggerFactory.getLogger(FenceMonitoringService.class);

    @Autowired
    private FenceService fenceService;

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private TwilioSMSService twilioSMSService;

    /**
     * Monitors fence entry and exit based on the given location.
     * Logs entry or exit events and triggers appropriate actions.
     *
     * @param location The location to monitor against the active fence.
     */
    public void monitorFenceEntryExit(Location location) {
        // get active fence from the database
        Fence activeFence = fenceService.getActiveFence();

        if (activeFence == null) {
            logger.warn("No active fence found.");
            return;
        }

        logger.info("Active fence found");
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

    /**
     * Checks if a point (location) is inside the boundary of a given fence.
     *
     * @param point    The point (location) to check.
     * @param boundary The boundary of the fence.
     * @return true if the point is inside the fence boundary, false otherwise.
     */
    private boolean isInsideFence(Point point, Polygon boundary) {
        return boundary.contains(point);
    }

    /**
     * Creates an event log for the fence event and saves it.
     * Also sends an SMS notification using Twilio service.
     *
     * @param fence   The fence associated with the event.
     * @param message The message indicating whether it's an entry or exit event.
     */
    private void createAndSaveEventLog(Fence fence, String message) {
        EventLog eventLog = new EventLog(new Timestamp(System.currentTimeMillis()), message, fence);
        eventLogService.createEventLog(eventLog);
        twilioSMSService.sendSMS("Fence: " + fence.getName() + ", " + message);
    }
}
