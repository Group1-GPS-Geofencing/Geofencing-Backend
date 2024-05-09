package com.example.testapp.data.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fence::class], version = 1, exportSchema = false)
abstract class FenceDatabase: RoomDatabase() {
    abstract fun userDao(): FenceDao

    companion object {
        @Volatile
        private var INSTANCE: FenceDatabase? = null

        fun getDatabase(context: Context): FenceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FenceDatabase::class.java,
                    "Fence_database"
                ).build()
                INSTANCE = instance
                instance


            }
        }
    }
}










