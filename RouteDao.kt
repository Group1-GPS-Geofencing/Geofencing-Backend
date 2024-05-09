package com.example.testapp.data.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

/**
 * create an interface because dao needs to be in an interface and name it RouteDao and annotate the interface with the Dao annotation
 * this will contain the methods used for accessing the database
 * and it has the necessary functions to execute inside  the database
 * we add the suspend key word in front of the functions because we are going to use the coroutines later
 * the first function is used to insert one or more Route objects (rows) into the database(The vararg parameter allows you to insert multiple routes in a single call)
 * the second function is for deleting  a specific Route object (row) from the database(The user parameter represents the route you want to delete)
 * the last function retrieves all records (rows) from the routes table in the database(the result is a list of Route objects).
 */

@Dao
interface RouteDao {
        @Insert
        suspend  fun insertAll(vararg users: Route)

        @Delete
        suspend fun delete(user: Route)

        @Update
        suspend fun updateRoute(vararg users: Fence)


        @Query("SELECT * FROM routes")
        suspend fun getAll(): List<Route>
    }

