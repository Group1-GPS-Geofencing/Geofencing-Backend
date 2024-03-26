package com.geofence.geofencing_backend.entities;

/*
 * Fence entity
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 26-03-2024
 * Last Modified on: 26-03-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalDate time;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "fence_id")
    private Fence fence;

    //no args constructor
    public EventLog() {
    }

    //args constructor
    public EventLog(LocalDate date, LocalDate time, String message, Fence fence) {
        this.date = date;
        this.time = time;
        this.message = message;
        this.fence = fence;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Fence getFence() {
        return fence;
    }

    public void setFence(Fence fence) {
        this.fence = fence;
    }

    //to string
    @Override
    public String toString() {
        return "EventLog{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", message='" + message + '\'' +
                ", fence=" + fence +
                '}';
    }

}
