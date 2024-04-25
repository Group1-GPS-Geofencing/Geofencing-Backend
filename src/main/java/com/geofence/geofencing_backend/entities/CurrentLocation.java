package com.geofence.geofencing_backend.entities;

/*
 * Fence entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 26-03-2024
 * Last Modified on: 25-04-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "CurrentLocation")
public class CurrentLocation {

    @Id
    @SequenceGenerator(
            name = "location_sequence",
            sequenceName = "location_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "location_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "point", columnDefinition = "geometry")
    private Point point;

    //added no arg constructor implicitly to overcome a runtime error that requires it
    public CurrentLocation(){

    }

    //args constructor
    @JsonCreator
    public CurrentLocation(@JsonProperty("time") Timestamp time,
                           @JsonProperty("point") Point point) {
        this.time = time;
        this.point = point;
    }

    //getters, setters, and toString methods/ functions are handled by Lombok
}
