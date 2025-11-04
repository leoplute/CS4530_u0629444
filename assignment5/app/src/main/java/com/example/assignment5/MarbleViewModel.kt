package com.example.assignment5

import androidx.lifecycle.ViewModel

class MarbleViewModel(private val repo : MarbleRepository) : ViewModel(){

    val marbleReading = repo.get

}