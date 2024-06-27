package com.onedev.testeratani._4_api_call.interfaces

import com.onedev.testeratani._4_api_call.model.Register
import com.onedev.testeratani._4_api_call.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @POST("users")
    suspend fun registerUser(
        @Body user: Register
    ): Response<User>
}