package com.geofence.geofencing_backend.services;

/*
 * Fence Monitoring Service; for detecting fence entry or exit
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 16-05-2024
 * Last Modified on: 25-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.Fence;
import com.geofence.geofencing_backend.entities.Location;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FenceMonitoringService {

    //logger
    private final Logger logger = LoggerFactory.getLogger(FenceMonitoringService.class);

    @Autowired
    private FenceService fenceService;

    // Helper method to monitor fence entry/exit based on location
    // Focus is only on exit
    public void monitorFenceEntryExit(Location location, TwilioSMSService twilioSMSService) {
        // get active fence from the database
        Fence activeFence = fenceService.getActiveFence();

        // Check if the location falls within the fence boundary
        if (isInsideFence(location.getPoint(), activeFence.getBoundary())) {
            // User entered the fence, trigger entry logic
            logger.info("Fence not exited");
        } else {
            // User exited the fence, trigger exit logic
            logger.info("Fence exited");
            twilioSMSService.sendSMS("User exited fence: " + activeFence.getName());
            // Todo: create and save an event log
        }

    }

    // Helper method to check if a point is inside a fence boundary/ polygon
    private boolean isInsideFence(Point point, Polygon boundary) {
        return boundary.contains(point);
    }
}
