package com.example.assignment4

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class factViewModel(private val repo : FactRepository) : ViewModel(){

    val factsReadOnly : StateFlow<List<FactData>> = repo.allFacts

}