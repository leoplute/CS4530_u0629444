package com.example.assignment5

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.channels.trySendBlocking
import kotlin.math.max
import kotlin.math.min


//marble pos on screen
data class MarbleState(val x : Float, val y : Float)

//sensor reading
data class GravReading(val x : Float, val y : Float)

class MarbleRepository(private val sensorManager : SensorManager) {

    //grav sensor if available, otherwise accelerometer
    private val gravSensor =
        sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
            ?: sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    //holds current marble position
    val marbState = MutableStateFlow(MarbleState(0f,0f))

    //motion + physics variables
    private var velX = 0f
    private var velY = 0f
    var maxWidth = 0f
    var maxHeight = 0f
    var marbSize = 0f
    private val scale = -3f
    private val fric = 0.9f

    // return a flow of grav readings from the sensor
    fun getMarbFlow() : Flow<GravReading> = channelFlow {
        if(gravSensor == null){
            return@channelFlow
        }
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                trySendBlocking(GravReading(x,y))
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        }
        sensorManager.registerListener(listener, gravSensor, SensorManager.SENSOR_DELAY_GAME)
        awaitClose { sensorManager.unregisterListener(listener) }
    }

    //physics for updating the marble pos
    fun updateMarble(gravX : Float, gravY : Float){
        //apply acceleration based on tilt
        velX += scale * gravX
        velY += scale * -gravY

        //update marbles pos
        var newX = marbState.value.x + velX
        var newY = marbState.value.y + velY

        //make sure its within screen
        newX = max(0f, min(newX, maxWidth - marbSize))
        newY = max(0f, min(newY, maxHeight - marbSize))

        //update state
        marbState.update {MarbleState(newX,newY)}
        //apply friction
        velX *= fric
        velY *= fric
    }

}