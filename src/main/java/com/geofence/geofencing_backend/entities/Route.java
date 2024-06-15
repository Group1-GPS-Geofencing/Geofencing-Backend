package com.geofence.geofencing_backend.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.*;
import org.slf4j.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * Route entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 17-04-2024
 * Last Modified on: 14-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Data
@Entity
@Table(name = "Route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "date")
    @JsonProperty("start_time")
    private Timestamp date;

    @OneToMany(mappedBy = "route", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonProperty("locations")
    private List<Location> locations = new ArrayList<>();

    @Column(name = "points", columnDefinition = "geometry")
    @JsonProperty("points")
    private LineString points;

    //Logger for logging
    private static final Logger logger = LoggerFactory.getLogger(Route.class);

    /**
     * Default no-args constructor.
     */
    public Route() {
        // Default constructor
    }

    /**
     * Argument constructor to create a Route with specified name, date and points.
     *
     * @param name  the name of the route
     * @param date  the start time of the route
     * @param points the points representing the route
     */
    @JsonCreator
    public Route(@JsonProperty("name") String name,
                 @JsonProperty("start_time") Timestamp date,
                 @JsonProperty("points") LineString points) {
        this.name = name;
        this.date = date;
        this.points = points;
    }

}
