package com.example.assignment4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="facts")
data class FactData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val text : String
)