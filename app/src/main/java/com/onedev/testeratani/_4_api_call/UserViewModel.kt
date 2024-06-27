package com.onedev.testeratani._4_api_call

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.testeratani._4_api_call.interfaces.RetrofitInstance
import com.onedev.testeratani._4_api_call.model.Register
import com.onedev.testeratani._4_api_call.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel : ViewModel() {
    var users = mutableStateListOf<User>()
        private set

    private val _createUserResponse = MutableStateFlow<Response<User>?>(null)
    val createUserResponse: StateFlow<Response<User>?> = _createUserResponse

    var isLoading = MutableStateFlow(false)
    var isLoadingList = MutableStateFlow(false)

    fun fetchUsers() {
        isLoadingList.value = true
        viewModelScope.launch {
            val response = RetrofitInstance.api.getUsers()
            if (response.isSuccessful) {
                response.body()?.let {
                    users.clear()
                    users.addAll(it)
                    isLoadingList.value = false
                }
            }
        }
    }

    fun registerUser(user: Register) {
        viewModelScope.launch {
            isLoading.value = true
            val response = RetrofitInstance.api.registerUser(user)
            _createUserResponse.value = response
            isLoading.value = false
        }
    }
}