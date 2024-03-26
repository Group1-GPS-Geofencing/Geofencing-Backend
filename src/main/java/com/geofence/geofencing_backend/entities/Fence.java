package com.geofence.geofencing_backend.entities;

/*
 * Fence entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 26-03-2024
 * Last Modified on: 26-03-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Fence")
public class Fence {

    @Id
    @SequenceGenerator(
            name = "fence_sequence",
            sequenceName = "fence_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fence_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "center_longitude")
    private Double centerLongitude;

    @Column(name = "center_latitude")
    private Double centerLatitude;

    @Column(name = "center_radius")
    private Double centerRadius;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "fence", cascade = CascadeType.ALL)
    private List<EventLog> logs;

    //no args constructor
    public Fence() {
    }

    //args constructor
    public Fence(String name, String description, Double centerLongitude, Double centerLatitude, Double centerRadius, Boolean isActive) {
        this.name = name;
        this.description = description;
        this.centerLongitude = centerLongitude;
        this.centerLatitude = centerLatitude;
        this.centerRadius = centerRadius;
        this.isActive = isActive;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCenterLongitude() {
        return centerLongitude;
    }

    public void setCenterLongitude(Double centerLongitude) {
        this.centerLongitude = centerLongitude;
    }

    public Double getCenterLatitude() {
        return centerLatitude;
    }

    public void setCenterLatitude(Double centerLatitude) {
        this.centerLatitude = centerLatitude;
    }

    public Double getCenterRadius() {
        return centerRadius;
    }

    public void setCenterRadius(Double centerRadius) {
        this.centerRadius = centerRadius;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<EventLog> getLogs() {
        return logs;
    }

    public void setLogs(List<EventLog> logs) {
        this.logs = logs;
    }

    //to string
    @Override
    public String toString() {
        return "Fence{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", centerLongitude=" + centerLongitude +
                ", centerLatitude=" + centerLatitude +
                ", centerRadius=" + centerRadius +
                ", isActive=" + isActive +
                ", logs=" + logs +
                '}';
    }

}
