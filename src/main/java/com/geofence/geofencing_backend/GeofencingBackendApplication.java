package com.geofence.geofencing_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GeofencingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeofencingBackendApplication.class, args);
	}

}
