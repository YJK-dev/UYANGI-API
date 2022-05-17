package com.example.uyangi.domain.auth

data class SignupRequest(
    val email: String,
    val name: String,
    val password: String,
    val fcmToken: String?
)