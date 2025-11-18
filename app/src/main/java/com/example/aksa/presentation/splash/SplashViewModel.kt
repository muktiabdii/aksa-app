package com.example.aksa.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksa.domain.usecase.OnBoardingUseCase
import com.example.aksa.domain.usecase.UserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userUseCase: UserUseCase,
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    private val _splashState = MutableStateFlow<SplashState>(SplashState.Loading)
    val splashState: StateFlow<SplashState> = _splashState

    init {
        checkStartDestination()
    }

    private fun checkStartDestination() {
        viewModelScope.launch {
            delay(2000)

            val isOnBoardingShown = onBoardingUseCase.getOnBoardingState().first()
            val isUserLoggedIn = userUseCase.isUserLoggedIn()

            if (isUserLoggedIn) {
                _splashState.value = SplashState.NavigateToHome
            } else {
                if (isOnBoardingShown) {
                    _splashState.value = SplashState.NavigateToLogin
                } else {
                    _splashState.value = SplashState.NavigateToOnBoarding
                }
            }
        }
    }

    fun setOnBoardingShown() {
        viewModelScope.launch {
            onBoardingUseCase.setOnBoardingState(true)
        }
    }
}

// State Navigation
sealed class SplashState {
    object Loading : SplashState()
    object NavigateToOnBoarding : SplashState()
    object NavigateToLogin : SplashState()
    object NavigateToHome : SplashState()
}