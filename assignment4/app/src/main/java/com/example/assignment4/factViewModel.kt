package com.example.assignment4

import android.R.attr.text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class factViewModel(private val repo : FactRepository) : ViewModel(){

    private val client = HttpClient(Android){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    val factsReadOnly : StateFlow<List<FactData>> = repo.allFacts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun add_fact() {
        viewModelScope.launch{
            repo.fetchSaveFact()
        }
    }

    fun delete_fact(fact : FactData){
        repo.deleteFact(fact)
    }

}


object FactViewModelProvider {
    val Factory = viewModelFactory{
        initializer {
            factViewModel(
                (this[AndroidViewModelFactory.APPLICATION_KEY]
                        as FactApplication).factRepository
            )
        }
    }
}