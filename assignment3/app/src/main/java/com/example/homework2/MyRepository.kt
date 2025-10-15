package com.example.homework2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MyRepository(private val scope: CoroutineScope,
                    private val dao : MyDAO){

    // repo, use passed in coursedata from VM to call dao functions w/ that
    //  ** In a launched coroutine scope

    val allCourses : Flow<List<CourseData>> = dao.allCourses()

    fun addCourse(course : CourseData){
        scope.launch {
            dao.addCourse(course)
        }
    }

    fun deleteCourse(course : CourseData){
        scope.launch {
            dao.deleteCourse(course)
        }
    }
}