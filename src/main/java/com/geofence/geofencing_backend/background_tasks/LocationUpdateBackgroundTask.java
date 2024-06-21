package com.geofence.geofencing_backend.background_tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.*;
import com.geofence.geofencing_backend.services.FirebaseService;

/**
 *  LocationUpdateBackgroundTask
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 02-05-2024
 * Last Modified on: 05-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 * <p>
 * Background task for fetching data from the firebase cloud DB (firestore) every minute
 */

@Component
public class LocationUpdateBackgroundTask {

    private final FirebaseService firebaseService;

    public LocationUpdateBackgroundTask(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    //Data Fetching background task, executing every  minute
    @Scheduled(fixedRate = 60000) // Execute every minute
    public void updateLocationData() {
        firebaseService.fetchAndSaveLocationData();
    }

}
