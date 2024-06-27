package com.onedev.testeratani._3_animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onedev.testeratani.ui.theme.TestErataniTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class HeartRateActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestErataniTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Heart Beat App") },
                            navigationIcon = {
                                IconButton(onClick = { onBackPressedDispatcher.onBackPressed() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    HeartBeatApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HeartBeatApp(modifier: Modifier = Modifier) {
    var bpm by remember { mutableIntStateOf(60) }
    var isBeating by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "BPM: $bpm", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(16.dp))
        HeartBeatCircle(isBeating = isBeating, bpm = bpm)
        Spacer(modifier = Modifier.height(16.dp))
        Slider(
            value = bpm.toFloat(),
            onValueChange = { bpm = it.toInt() },
            valueRange = 40f..200f
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { isBeating = !isBeating },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isBeating) Color.Red else Color.Green
            )
        ) {
            Text(if (isBeating) "Stop" else "Start")
        }
    }
}

@Composable
fun HeartBeatCircle(isBeating: Boolean, bpm: Int) {
    var scale by remember { mutableFloatStateOf(1f) }
    val delayMillis = (60000 / bpm).toLong() // Calculate delay based on BPM

    LaunchedEffect(isBeating, bpm) {
        while (isActive && isBeating) {
            scale = 1.5f
            delay(delayMillis / 2)
            scale = 1f
            delay(delayMillis / 2)
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(Color.Red)
    )
}

fun Modifier.scale(scale: Float) = this.then(
    Modifier.graphicsLayer(
        scaleX = scale,
        scaleY = scale
    )
)

@Preview(showBackground = true)
@Composable
fun HeartBeatPreview() {
    TestErataniTheme {
        HeartBeatApp()
    }
}