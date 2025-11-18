package com.example.aksa.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aksa.domain.usecase.OnBoardingUseCase
import com.example.aksa.domain.usecase.UserUseCase

class SplashViewModelFactory(
    private val userUseCase: UserUseCase,
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(userUseCase, onBoardingUseCase) as T
    }
}
