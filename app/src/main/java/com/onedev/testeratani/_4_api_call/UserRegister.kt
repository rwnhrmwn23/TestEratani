package com.onedev.testeratani._4_api_call

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.onedev.testeratani.showToast
import com.onedev.testeratani.ui.theme.TestErataniTheme
import com.onedev.testeratani._4_api_call.model.Register
import kotlinx.coroutines.launch

@Composable
fun UserRegister() {
    val viewModel: UserViewModel = viewModel()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val createUserResponse by viewModel.createUserResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Register User", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Gender", style = MaterialTheme.typography.bodyMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = gender == "male",
                onClick = { gender = "male" }
            )
            Text(text = "Male", modifier = Modifier.padding(start = 4.dp))
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = gender == "female",
                onClick = { gender = "female" }
            )
            Text(text = "Female", modifier = Modifier.padding(start = 4.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch {
                    if (name.isEmpty()) {
                        context.showToast("Please insert your name")
                    } else if (email.isEmpty()) {
                        context.showToast("Please insert your email")
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        context.showToast("Please insert valid email")
                    } else if (gender.isEmpty()) {
                        context.showToast("Please choose your gender")
                    } else {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        val newUser = Register(name, email, gender)
                        viewModel.registerUser(newUser)
                    }
                }
            },
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(24.dp)),
                    color = Color.White
                )
            } else {
                Text("Register")
            }
        }

        LaunchedEffect(createUserResponse) {
            createUserResponse?.let { response ->
                when (response.code()) {
                    201 -> {
                        context.showToast("User registered successfully")
                        viewModel.fetchUsers()
                    }

                    422 -> {
                        context.showToast("Email has already been taken")
                    }

                    else -> {
                        context.showToast("Failed to register user")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterUserPreview() {
    TestErataniTheme {
        UserRegister()
    }
}