package com.onedev.testeratani

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onedev.testeratani._1_searching_word.SearchWordActivity
import com.onedev.testeratani._2_processing_data.StockProductActivity
import com.onedev.testeratani._3_animation.HeartRateActivity
import com.onedev.testeratani._4_api_call.UserListActivity
import com.onedev.testeratani.ui.theme.TestErataniTheme
import kotlin.jvm.Throws

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestErataniTheme {
                MainMenu()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainMenu() {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Main Menu") })
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "ERATANI EMPLOYEE ASSESSMENT TEST")
                Text(text = "Department Technology Mobile Engineer")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Irwan Hermawan")
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, SearchWordActivity::class.java))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Search Word App")
                }

                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, StockProductActivity::class.java))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Stock Product App")
                }

                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, HeartRateActivity::class.java))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Heart Rate App")
                }

                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, UserListActivity::class.java))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("User List App")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainMenuPreview() {
        TestErataniTheme {
            MainMenu()
        }
    }
}