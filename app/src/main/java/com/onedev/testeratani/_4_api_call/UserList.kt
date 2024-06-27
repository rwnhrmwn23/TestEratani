package com.onedev.testeratani._4_api_call

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.onedev.testeratani._4_api_call.model.User
import com.onedev.testeratani.ui.theme.TestErataniTheme

@Composable
fun UserList() {

    val viewModel: UserViewModel = viewModel()
    val isLoadingList by viewModel.isLoadingList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoadingList) {
            CircularProgressIndicator()
        } else {
            TableUsers(viewModel.users)
        }
    }
}

@Composable
fun TableUsers(users: List<User>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                text = "Name",
            )
            Text(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp),
                text = "Email"
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                text = "Gender"
            )
        }
        Divider()
        users.forEach { user ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    text = user.name
                )
                Text(
                    modifier = Modifier
                        .weight(2f)
                        .padding(horizontal = 8.dp),
                    text = user.email
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    text = user.gender
                )
            }
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TableUserPreview() {
    TestErataniTheme {
        val listUser = listOf(
            User(1, "Irwan", "irwan@gmail.com", "male", "active"),
            User(2, "Romi", "romi@gmail.com", "male", "active"),
            User(3, "Ranti", "ranti@gmail.com", "female", "inactive"),

            )
        TableUsers(listUser)
    }
}