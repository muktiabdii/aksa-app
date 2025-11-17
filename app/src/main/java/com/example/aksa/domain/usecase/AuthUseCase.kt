package com.example.aksa.domain.usecase

import android.util.Patterns
import com.example.aksa.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {

    // function to login
    suspend fun login(
        email: String,
        password: String
    ): Result<String> {
        validateLogin(email, password)?.let { return Result.failure(Exception(it)) }

        return runCatching {
            authRepository.login(email.trim(), password.trim())
        }
    }

    // function to register
    suspend fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Result<Unit> {

        validateRegister(name, email, password, passwordConfirmation)?.let {
            return Result.failure(Exception(it))
        }

        return runCatching {
            authRepository.register(
                name.trim(),
                email.trim(),
                password.trim(),
                passwordConfirmation.trim()
            )
        }
    }

    // function to sign in with google
    suspend fun signInWithGoogle(idToken: String): Result<String> {
        return runCatching { authRepository.signInWithGoogle(idToken) }
    }

    // function to forgot password
    suspend fun forgotPassword(email: String): Result<Unit> {
        validateForgotPassword(email)?.let { return Result.failure(Exception(it)) }

        return runCatching {
            authRepository.forgotPassword(email.trim())
        }
    }

    // function validasi login
    private fun validateLogin(email: String, password: String): String? {
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            return "Email dan password harus diisi"
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Format email salah"
        }

        if (password.length < 8) {
            return "Password harus lebih dari 8 karakter"
        }
        return null
    }

    // function validasi register
    private fun validateRegister(name: String, email: String, password: String, passwordConfirmation: String): String? {
        if (name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || passwordConfirmation.trim().isEmpty()) {
            return "Semua field harus diisi"
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Format email salah"
        }

        if (password.length < 8) {
            return "Password harus lebih dari 8 karakter"
        }

        if (password != passwordConfirmation) {
            return "Konfirmasi password tidak sesuai"
        }
        return null
    }

    // function validasi forgot password
    private fun validateForgotPassword(email: String): String? {
        if (email.trim().isEmpty()) {
            return "Email harus diisi"
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Format email salah"
        }
        return null
    }
}
