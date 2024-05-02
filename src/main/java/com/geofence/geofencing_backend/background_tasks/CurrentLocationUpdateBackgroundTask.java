package com.geofence.geofencing_backend.background_tasks;

/* CurrentLocationUpdateBackgroundTask
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 02-05-2024
 * Last Modified on: 02-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 *
 * Background task for fetching data from the firebase cloud DB (firestore) every 3 minutes
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.*;
import com.geofence.geofencing_backend.services.FirebaseService;

@Component
public class CurrentLocationUpdateBackgroundTask {

    private final FirebaseService firebaseService;

    public CurrentLocationUpdateBackgroundTask(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    //Data Fetching background task, executing every  3 minutes
    @Scheduled(fixedRate = 180000) // Execute every 3 minutes
    public void updateLocationData() {
        firebaseService.fetchAndSaveLocationData();
    }

}
