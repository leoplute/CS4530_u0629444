package com.example.homework2

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {

    val scope = CoroutineScope (SupervisorJob())

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "database"
        ).build()
    }

    val mainRepository by lazy { MyRepository(scope, db.myDao()) }
}