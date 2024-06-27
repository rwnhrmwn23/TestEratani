package com.onedev.testeratani._4_api_call.model

data class Register(
    val name: String,
    val email: String,
    val gender: String,
    val status: String = "active"
)