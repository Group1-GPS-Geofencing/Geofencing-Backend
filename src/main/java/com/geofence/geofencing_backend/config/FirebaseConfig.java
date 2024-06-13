package com.geofence.geofencing_backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.*;
import org.springframework.context.annotation.*;

import java.io.*;

/**
 * FirebaseConfig
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 02-05-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 * Configures and initializes Firebase using the provided API key
 */

@Configuration
public class FirebaseConfig {

    /**
     * Initializes and provides a FirebaseApp instance.
     *
     * @return the initialized FirebaseApp instance
     * @throws IOException if there is an issue reading the service account file
     */
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // Path to Firebase service account JSON file
        String serviceAccountPath = "config/api_keys/geofencing_firebase_api_key.json";

        // Initialize Firebase Admin SDK
        FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }

}
