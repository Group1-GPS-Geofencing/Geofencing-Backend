package com.geofence.geofencing_backend.entities;

/*
 * Route entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 17-04-2024
 * Last Modified on: 28-05-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.*;
import org.slf4j.*;

import java.sql.Timestamp;
import java.util.*;

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

    //no args constructor
    public Route() {

    }

    //argument constructor
    @JsonCreator
    public Route(@JsonProperty("name") String name,
                 @JsonProperty("start_time") Timestamp date,
                 @JsonProperty("points") LineString points) {
        this.name = name;
        this.date = date;
        this.points = points;
    }

    //getters, setters, and toString methods/ functions are handled by Lombok

    // adds location to a route
    public void addLocation(Location location) {
        locations.add(location);
        location.setRoute(this);

        //todo: fix bug, points linestring is never updated, always Empty
        logger.info("Updating Points");
        updatePoints();
        logger.info("Location added to route: " + location);
    }

    // todo: fix bugs here
    // updates a route's linestring of points
    private void updatePoints() {
        Coordinate[] coordinates = locations.stream()
                .map(location -> new Coordinate(location.getPoint().getX(), location.getPoint().getY()))
                .toArray(Coordinate[]::new);
        logger.info("Updating Points: Initialised Coordinate Array");
        if (coordinates.length > 0) {
            logger.info("Coordinate Array size > 0 & points are: " + Arrays.toString(coordinates));
            this.points = new GeometryFactory().createLineString(coordinates);
            logger.info("Updated Points in route: " + Arrays.toString(coordinates));
        }
        else {
            logger.info("Coordinate Array size !> 0");
            logger.info("Failed to Update Linestring: ");
        }
        logger.info("Points Updated");
    }

}
