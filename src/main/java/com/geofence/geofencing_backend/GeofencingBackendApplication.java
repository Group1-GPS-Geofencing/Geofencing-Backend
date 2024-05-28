package com.geofence.geofencing_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class GeofencingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeofencingBackendApplication.class, args);
	}

}
