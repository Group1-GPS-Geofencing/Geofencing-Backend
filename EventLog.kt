package com.example.testapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.sql.Timestamp

/**
 * this is another of the entities of our application database
 * this is one of the tables in the database with the table name event logs
 * this event logs table has four fields: fenceId(FK), time, message
 * and above the logId we are going to add the annotation @Entity. our primary key is the logId and it is going to be auto generated and our default logId is equal to 0
 * room library is going to be automatically generate the logIds
 */

@Entity(tableName = "event logs")
data class EventLog(
    @PrimaryKey(autoGenerate = true)
    val logId: Int,

    val fenceId: Int,
    val time: Timestamp,
    val message: Text


)
