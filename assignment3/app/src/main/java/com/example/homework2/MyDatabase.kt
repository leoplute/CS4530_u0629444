package com.example.homework2

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.flow.Flow

@Database(entities = [CourseData::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDAO
}

@Dao
interface myDAO {

    @Insert
    suspend fun addCourse(data : CourseData)

    @Delete
    suspend fun deleteCourse(data : CourseData) : Flow<CourseData?>

    @Query
    fun allCourses() : Flow<List<CourseData>>
}