package com.example.homework2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework2.ui.theme.Homework2Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Homework2Theme {
                val vm:MainViewModel = viewModel()
                ClassAdder(vm)
                }
            }
        }
    }


@Composable
fun ClassAdder(myVm : MainViewModel){

    val observableCourses by myVm.coursesReadOnly.collectAsState()
    var departmentInput by remember { mutableStateOf("") }
    var courseNumberInput by remember { mutableStateOf("") }
    var locationInput by remember { mutableStateOf("") }
    var detailsInput by remember { mutableStateOf("") }

    var expandedCourse by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Classes",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ){
            TextField(
                value = departmentInput,
                onValueChange = { departmentInput = it },
                label = { Text("Dept")},
                modifier = Modifier.weight(1f).padding(start = 8.dp)
                )

            TextField(
                value = courseNumberInput,
                onValueChange = { courseNumberInput = it },
                label = { Text("Number") },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
                )

            TextField(
                value = locationInput,
                onValueChange = { locationInput = it },
                label = { Text("Location") },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
                )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            TextField(
                value = detailsInput,
                onValueChange = { detailsInput = it },
                label = { Text("Details")},
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            )
        }



        Button(
            onClick = {
                myVm.add_course(
                    courseNum = courseNumberInput.toInt(),
                    dept = departmentInput,
                    location = locationInput,
                    details = detailsInput
                )
                departmentInput = ""
                courseNumberInput = ""
                locationInput = ""
                detailsInput = ""
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text="Add course")
        }

        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            items(observableCourses) { course ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        // add clickable so the clicked course will expand to show details
                        .clickable {
                            // if the course is being clicked is not currently 'expandedCourse',
                            // set it as such, if its already the expanded course, make null,
                            // meaning there is no longer an expanded course showing details
                            expandedCourse = if(expandedCourse == course) null else course
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // dept + course num always visible
                        Text(text = "$department $courseNumber")

                        // show details only when expanded
                        if (expandedCourse == course) {
                            Text(
                                text = "Location: $location",
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = "Details: $details",
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            // only show the delete course button on the expanded course (currently selected course_
                            Button(
                                onClick = {
                                    myVm.delete_course(course)
                                    expandedCourse = null
                                },
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Delete Course")
                            }
                        }
                    }
                }
            }
        }
    }
}