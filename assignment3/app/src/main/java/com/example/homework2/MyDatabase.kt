package com.example.homework2

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [CourseData::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDAO
}

@Dao
interface MyDAO {

    @Insert
    suspend fun addCourse(data : CourseData)

    @Delete
    suspend fun deleteCourse(data : CourseData)

    @Query("select * from courses")
    fun allCourses() : Flow<List<CourseData>>
}