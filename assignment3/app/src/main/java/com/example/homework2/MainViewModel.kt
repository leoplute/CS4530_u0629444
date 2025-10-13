package com.example.homework2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.util.Date

class MainViewModel(private val repo : MyRepository) : ViewModel() {

    private val coursesMutable = MutableStateFlow(listOf<String>())
    val coursesReadOnly : StateFlow<List<String>> = coursesMutable

    fun add_course(course : String){
        coursesMutable.value = coursesMutable.value + course
    }

    fun delete_course(course : String){
        coursesMutable.value = coursesMutable.value - course
    }

}