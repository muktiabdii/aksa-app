package com.example.aksa.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): String
    suspend fun register(name: String, email: String, password: String, passwordConfirmation: String): Unit
    suspend fun signInWithGoogle(idToken: String): String
    suspend fun forgotPassword(email: String): Unit
}