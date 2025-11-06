package com.example.assignment5

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MarbleViewModel(private val repo : MarbleRepository) : ViewModel(){

    val marbReading = repo.getMarbFlow()
        .map { grav ->
            repo.updateMarble(grav.x, grav.y)
            repo.marbState.value
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            MarbleState(0f, 0f)
        )

    fun updateScreenSize(widthPx : Float, heightPx : Float, marblePx : Float) {
        repo.maxWidth = widthPx
        repo.maxHeight = heightPx
        repo.marbSize = marblePx
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarbleApp)
                MarbleViewModel(application.marbleRepository)
            }
        }
    }

}