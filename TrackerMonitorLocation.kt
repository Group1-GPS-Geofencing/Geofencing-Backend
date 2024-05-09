package com.example.testapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

/**
 * this is another  entity of our application's database
 * this is one of the tables in the database with the table name trackerMl
 * this routes table has 3 fields: locationId, point and time
 * our primary key is the locationId and it is going to be auto generated and our default locationId is equal to 0
 * our time is going to be given in timestamps and the points in texts(strings) and the locationId in integers
 */

@Entity(tableName = "trackerMl")
data class TrackerMonitorLocation(
    @PrimaryKey(autoGenerate = true)
    val locationId: Long = 0,
    val point: String,
    val time: Timestamp
)