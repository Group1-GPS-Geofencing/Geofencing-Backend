package com.geofence.geofencing_backend.services;

/*
 * FenceMonitoringServiceTest
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 29-05-2024
 * Last Modified on: 30-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.geofence.geofencing_backend.entities.*;
import com.geofence.geofencing_backend.repositories.EventLogRepository;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

@SpringBootTest
public class FenceMonitoringServiceTest {

    @Autowired
    private FenceMonitoringService fenceMonitoringService;

    @MockBean
    private FenceService fenceService;

    @MockBean
    private EventLogRepository eventLogRepository;

    @MockBean
    private TwilioSMSService twilioSMSService;

    @Test
    public void testMonitorFenceEntry() {
        // Mock data
        Fence fence = new Fence(1L, "Test Fence", "Unit tesing a fence", createPolygon(), true);
        Location location = new Location(new Timestamp(System.currentTimeMillis()), createPoint(1.0, 1.0));

        // Mock methods
        Mockito.when(fenceService.getActiveFence()).thenReturn(fence);

        // Call method
        fenceMonitoringService.monitorFenceEntryExit(location);

        // Verify entry log
        Mockito.verify(eventLogRepository, Mockito.times(1)).save(Mockito.any(EventLog.class));
    }

    @Test
    public void testMonitorFenceExit() {
        // Mock data
        Fence fence = new Fence(1L, "Test Fence", "Description", createPolygon(), true);
        Location location = new Location(new Timestamp(System.currentTimeMillis()), createPoint(10.0, 10.0));

        // Mock methods
        Mockito.when(fenceService.getActiveFence()).thenReturn(fence);

        // Call method
        fenceMonitoringService.monitorFenceEntryExit(location);

        // Verify exit log
        Mockito.verify(eventLogRepository, Mockito.times(1)).save(Mockito.any(EventLog.class));
        Mockito.verify(twilioSMSService, Mockito.times(1)).sendSMS(Mockito.anyString());
    }

    private Polygon createPolygon() {
        GeometryFactory factory = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(0, 5),
                new Coordinate(5, 5),
                new Coordinate(5, 0),
                new Coordinate(0, 0)
        };
        return factory.createPolygon(coordinates);
    }

    private Point createPoint(double x, double y) {
        GeometryFactory factory = new GeometryFactory();
        return factory.createPoint(new Coordinate(x, y));
    }
}
