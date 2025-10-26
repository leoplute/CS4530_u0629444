package com.example.assignment4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="facts")
data class FactData(
    @PrimaryKey
    val id : String,
    val text : String
)