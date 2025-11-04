package com.example.assignment5

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.Flow

data class MarbleData(val x : Float, val y : Float, val z : Float)

class MarbleRepository(private val sensorManager : SensorManager) {

    fun getGravityFlow() : Flow<MarbleData> = channelFlow {
        val gravSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        val sensor = gravSensor ?: sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if(sensor == null){
            return@channelFlow
        }

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                trySendBlocking(MarbleData(event.values[0], event.values[1], event.values[2]))
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy : Int) = Unit
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME)
        awaitClose { sensorManager.unregisterListener(listener) }
    }

}