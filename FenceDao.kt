package com.example.testapp.data.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import androidx.room.Delete

/**
 * create an interface because dao needs to be in an interface and name it RouteDao and annotate the interface with the Dao annotation
 * this will contain the methods used for accessing the database
 * and it has the necessary functions to execute inside  the database
 * we add the suspend key word in front of the functions because we are going to use the coroutines later.
 * the OnConflict indicates that if there's a conflict (e.g., a row with the same primary key already exists), the insertion operation should be ignored and no operation should be made.
 * the first function provided is a suspend function that inserts a Fence object into the corresponding table.
 * the second function is responsible for deleting one or more Fence objects from the database table associated with this DAO.
 * the third function is responsible for updating one or more Fence objects from database table associated with this Dao
 *  the fourth function 'readAllData' function retrieves all records from the Fences table, ordered by their IDs in ascending order, and returns them as a LiveData object.
 */


@Dao
interface FenceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFence(user: Fence)

    @Delete
   suspend fun deleteFence(vararg users: Fence)

    @Update
    suspend fun updateFence(vararg users: Fence)


    @Query("SELECT * FROM Fences ORDER BY id ASC")
    suspend fun readAllData(): LiveData<List<Fence>>
}