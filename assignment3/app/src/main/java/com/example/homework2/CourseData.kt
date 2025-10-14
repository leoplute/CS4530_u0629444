package com.example.homework2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="courses")
data class CourseData(var courseNumber : Int,
                      var dept : String,
                      var location : String,
                      var details : String)
{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}