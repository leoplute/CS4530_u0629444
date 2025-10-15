package com.example.homework2

import androidx.room.Entity

// db table, key combo so no dept can have a class w/ same number
@Entity(tableName="courses", primaryKeys = ["dept", "courseNumber"])
data class CourseData(var courseNumber : Int,
                      var dept : String,
                      var location : String,
                      var details : String)