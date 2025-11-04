package com.example.assignment5

import android.hardware.SensorManager

data class MarbleData(val x : Float = 0f, val y : Float = 0f, val velocityX : Float = 0f, val velocityY : Float = 0f)

class MarbleRepository(private val sensorManager : SensorManager) {

}