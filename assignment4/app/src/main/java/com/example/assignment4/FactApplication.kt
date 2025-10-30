package com.example.assignment4

import android.app.Application
import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json

class FactApplication : Application() {

    val scope = CoroutineScope(SupervisorJob())

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            FactDatabase::class.java,
            "database"
        ).build()
    }

    val ktorClient by lazy {
        HttpClient(Android){
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    val factRepository by lazy { FactRepository(scope, db.factDao(), ktorClient) }

}