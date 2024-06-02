package com.geofence.geofencing_backend.entities;

/*
 * Location entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 26-03-2024
 * Last Modified on: 28-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Location")
public class Location {

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
    @JsonProperty("id")
    private Long id;

    @Column(name = "time")
    @JsonProperty("time")
    private Timestamp time;

    @Column(name = "point", columnDefinition = "geometry")
    @JsonProperty("point")
    private Point point;

    // Assuming many locations can belong to one route,
    // FetchType.LAZY indicates that the route information will be fetched lazily when accessed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = true)
    @JsonBackReference
    @JsonProperty("route")
    private Route route;


    //added no arg constructor implicitly to overcome a runtime error that requires it
    public Location(){

    }

    //args constructor
    @JsonCreator
    public Location(@JsonProperty("time") Timestamp time,
                    @JsonProperty("point") Point point) {
        this.time = time;
        this.point = point;
    }

    //getters, setters, and toString methods/ functions are handled by Lombok
}
