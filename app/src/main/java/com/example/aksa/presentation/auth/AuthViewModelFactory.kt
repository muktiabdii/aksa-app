package com.example.aksa.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aksa.domain.usecase.AuthUseCase
import com.example.aksa.domain.usecase.UserUseCase

class AuthViewModelFactory(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(authUseCase, userUseCase) as T
    }
}
