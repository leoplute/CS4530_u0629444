package com.example.assignment4

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FactApplication : Application() {

    val scope = CoroutineScope(SupervisorJob())

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            FactDatabase::class.java,
            "database"
        ).build()
    }

    val mainRepository by lazy { FactRepository(scope, db.factDao()) }

}