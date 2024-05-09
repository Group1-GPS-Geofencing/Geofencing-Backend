package com.example.testapp.data.entities

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete

/**
 * create an interface because dao needs to be in an interface and name it TrackerMonitorLocation Dao and annotate the interface with the Dao annotation
 * this will contain the methods used for accessing the database
 * and it has the necessary queries to execute inside  the database
 * we add the suspend key word in front of the functions because we are going to use the coroutines later
 * first function is used to delete records from a database table trackerMl based on a specified locationId.
 * the second function is used to retrieve all records from the trackerMl table(The function takes no parameters and returns a list of objects of type TrackerMonitorLocation).
 * the last function deletes a specific record (represented by the trackerMl parameter) from a database table.
 */


@Dao
interface TrackerMonitorLocationDao {

    @Query("DELETE FROM trackerMl WHERE locationId = :id")
    suspend fun deleteTrackerMonitorLocationById(id: Long)

    @Query("SELECT * FROM trackerMl")
    suspend fun getAllTrackerMl(): List<TrackerMonitorLocation>

    @Delete
    suspend fun deleteTrackerMonitorLocation(trackerMl: TrackerMonitorLocation)


}