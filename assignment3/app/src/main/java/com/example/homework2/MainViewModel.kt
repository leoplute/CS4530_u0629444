package com.example.homework2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val repo : MyRepository) : ViewModel() {

    val coursesReadOnly : StateFlow<List<CourseData>> = repo.allCourses
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun add_course(dept : String, courseNum : Int, location : String, details : String){
        val course = CourseData(
            courseNumber = courseNum,
            dept = dept,
            location = location,
            details = details
        )
        repo.addCourse(course)
    }

    fun delete_course(course : CourseData){
        repo.deleteCourse(course)
    }

}

object MainViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MainViewModel(
                (this[AndroidViewModelFactory.APPLICATION_KEY]
                        as MainApplication).mainRepository
            )
        }
    }
}