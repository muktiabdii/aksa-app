package com.example.aksa.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.aksa.domain.usecase.AuthUseCase
import com.example.aksa.domain.usecase.UserUseCase
import com.example.aksa.presentation.auth.AuthViewModel

class ViewModelFactory (
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(authUseCase, userUseCase) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}