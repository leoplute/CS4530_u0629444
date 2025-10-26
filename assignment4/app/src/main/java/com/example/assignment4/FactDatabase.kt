package com.example.assignment4

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [FactData::class], version = 1, exportSchema = false)
abstract class FactDatabase : RoomDatabase() {
    abstract fun factDao() : FactDao
}

@Dao
interface FactDao {

    @Insert
    suspend fun addFact(fact : FactData)

    @Delete
    suspend fun deleteFact(fact : FactData)

    @Query("select * from facts")
    fun allFacts() : Flow<List<FactData>>

}