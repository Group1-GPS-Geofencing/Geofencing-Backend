package com.geofence.geofencing_backend.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

/**
 * Fence entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 26-03-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Data
@Entity
@Table(name = "Fence")
public class Fence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "boundary", columnDefinition = "geometry")
    @JsonProperty("boundary")
    private Polygon boundary;

    @Column(name = "is_active")
    @JsonProperty("is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "fence", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EventLog> logs;


    /**
     * Default no-args constructor.
     */
    public Fence(){
        // Default constructor
    }

    /**
     * Argument constructor to create a Fence with specified id, name, description, boundary, and active status.
     *
     * @param id          the id of the fence
     * @param name        the name of the fence
     * @param description the description of the fence
     * @param boundary    the boundary of the fence as a Polygon
     * @param isActive    the active status of the fence
     */
    @JsonCreator
    public Fence(@JsonProperty("id") Long id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 @JsonProperty("boundary") Polygon boundary,
                 @JsonProperty("is_active") Boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.boundary = boundary;
        this.isActive = isActive;
    }

    //getters/ setters, and toString are handled by Lombok
}
