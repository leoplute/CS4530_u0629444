package com.example.assignment5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MarbleViewModel(private val repo : MarbleRepository) : ViewModel(){

    val marbleReading = repo.getGravityFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            MarbleData(0f, 0f, 0f)
        )

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarbleApp)
                MarbleViewModel(application.marbleRepository)
            }
        }
    }

}