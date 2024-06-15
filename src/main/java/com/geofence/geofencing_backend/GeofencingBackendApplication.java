package com.geofence.geofencing_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main application class for the Geofencing Backend Application.
 * Enables scheduling and transaction management.
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 17-04-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class GeofencingBackendApplication {

	/**
	 * The entry point of the Geofencing Backend Application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(GeofencingBackendApplication.class, args);
	}

}
