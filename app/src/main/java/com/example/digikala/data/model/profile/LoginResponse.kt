package com.example.digikala.data.model.profile

data class LoginResponse(
    val phone: String,
    val name: String? = null,
    val id: String,
    val role: String,
    val token: String
)
