package com.geofence.geofencing_backend.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.sql.Timestamp;

/**
 * Location entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 26-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

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

    /**
     * Many locations can belong to one route. FetchType.LAZY indicates that
     * the route information will be fetched lazily when accessed.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = true)
    @JsonBackReference
    @JsonProperty("route")
    private Route route;

    /**
     * Default no-args constructor.
     */
    public Location(){
        // Default constructor
    }

    /**
     * Argument constructor to create a Location with specified time and point.
     *
     * @param time  the timestamp of the location
     * @param point the geographical point of the location
     */
    @JsonCreator
    public Location(@JsonProperty("time") Timestamp time,
                    @JsonProperty("point") Point point) {
        this.time = time;
        this.point = point;
    }

    //getters, setters, and toString methods/ functions are handled by Lombok
}
