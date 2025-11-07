package com.example.assignment5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.assignment5.ui.theme.Assignment5Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment5Theme {
                val vm : MarbleViewModel = viewModel(factory = MarbleViewModel.Factory)
                MarbleScreen(vm)
            }
        }
    }
}

@Composable
fun MarbleScreen(viewModel : MarbleViewModel) {

    //collect marble state
    val marble = viewModel.marbReading.collectAsStateWithLifecycle().value
    val marbleSize = 40.dp

    BoxWithConstraints(
        modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ) {
        val maxW = maxWidth
        val maxH = maxHeight

        //when layout size changes, update vm w/ new numbers
        LaunchedEffect(maxW, maxH) {
            viewModel.updateScreenSize(
                width = maxW,
                height = maxH,
                marbleSize = marbleSize
            )
        }

        //draw a blue circle for a marble at the stored x,y pos
        Box(
            modifier = Modifier
                .offset(x = marble.x.dp, y = marble.y.dp)
                .size(marbleSize)
                .background(Color.Blue, CircleShape)
        )
    }

}