package com.example.testapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Fences")
data class Fence(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val fenceName: String,
    val description: String,
    val boundary: Int,
    val isActive: String,
)