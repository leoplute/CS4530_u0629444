package com.example.homework2

import kotlinx.coroutines.CoroutineScope

class MyRepository(val scope: CoroutineScope,
                    private val dao : MyDAO){

    val allCourses : Flow<CourseData?> = dao.allCourses()

}