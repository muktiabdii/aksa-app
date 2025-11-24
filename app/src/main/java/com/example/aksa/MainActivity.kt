package com.example.aksa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.aksa.data.datastore.UserPreferencesManager
import com.example.aksa.data.repository.AuthRepositoryImpl
import com.example.aksa.data.repository.UserRepositoryImpl
import com.example.aksa.domain.usecase.AuthUseCase
import com.example.aksa.domain.usecase.OnBoardingUseCase
import com.example.aksa.domain.usecase.UserUseCase
import com.example.aksa.presentation.akview.museum.ViewVirtualScreen
import com.example.aksa.ui.theme.AksaTheme

class MainActivity : ComponentActivity() {

    private val userPreferencesManager by lazy {
        UserPreferencesManager(this)
    }
    private val userRepo by lazy {
        UserRepositoryImpl(userPreferencesManager, this)
    }
    private val userUseCase by lazy {
        UserUseCase(userRepo)
    }
    private val authRepo by lazy {
        AuthRepositoryImpl()
    }
    private val authUseCase by lazy {
        AuthUseCase(authRepo)
    }
    private val onBoardingUseCase by lazy {
        OnBoardingUseCase(userPreferencesManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AksaTheme {
                AppNavHost(
                    userUseCase = userUseCase,
                    authUseCase = authUseCase,
                    onBoardingUseCase = onBoardingUseCase
                )
            }
        }
    }
}