package com.geofence.geofencing_backend.entities;

/*
 * Fence entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 26-03-2024
 * Last Modified on: 25-04-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "EventLog")
public class EventLog {

    @Id
    @SequenceGenerator(
            name = "log_sequence",
            sequenceName = "log_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "log_sequence"
    )
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "time")
    @JsonProperty("time")
    private Timestamp time;

    @Column(name = "message")
    @JsonProperty("message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "fence_id")
    @JsonBackReference
    private Fence fence;

    //added no arg constructor implicitly to overcome a runtime error that requires it
    public EventLog(){

    }

    //args constructor
    @JsonCreator
    public EventLog(@JsonProperty("time") Timestamp time,
                    @JsonProperty("message") String message,
                    @JsonProperty("fence") Fence fence) {
        this.time = time;
        this.message = message;
        this.fence = fence;
    }

    //getters, setters, and toString methods/ functions are handled by Lombok
}
