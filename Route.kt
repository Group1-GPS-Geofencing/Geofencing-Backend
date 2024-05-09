package com.example.testapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

/**
 * this is one of the entities of our application database
 * this is one of the tables in the database with the table name routes
 * this routes table has five fields: routeId, name,startTime,endTime and points
 * and above the routeId we are going to add the annotation @Entity. our primary key is the routeId and it is going to be auto generated and our default routeId is equal to 0
 * room library is going to be automatically generate the Ids
 */
@Entity(tableName = "routes")
data class Route(
    @PrimaryKey(autoGenerate = true)
    val routeId: Long = 0,
    val name: String,
    val startTime: Timestamp,
    val endTime: Timestamp,
    val point: String
)
