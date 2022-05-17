package com.example.uyangi.domain.auth

data class SigninRequest(
    val email: String,
    val password: String,
    val fcmToken: String?
)
