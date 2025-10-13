package com.example.homework2

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.vo.Entity
import java.util.Date

@Entity(tableName="courses")
data class CourseData(var courseNumber : Int,
                      var dept : String,
                      var location : String,
                      var details : String)
{
    @PrimaryKey
    var courseNumber : Int = courseNumber,
    var dept : String = dept,
    var location : String = location,
    var details : String = details
}