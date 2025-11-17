package com.example.aksa

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aksa.data.datastore.UserPreferencesManager
import com.example.aksa.data.repository.AuthRepositoryImpl
import com.example.aksa.data.repository.UserRepositoryImpl
import com.example.aksa.domain.usecase.AuthUseCase
import com.example.aksa.domain.usecase.OnBoardingUseCase
import com.example.aksa.domain.usecase.UserUseCase
import com.example.aksa.presentation.auth.AuthViewModel
import com.example.aksa.presentation.auth.ForgotPasswordScreen
import com.example.aksa.presentation.auth.LoginScreen
import com.example.aksa.presentation.auth.RegisterScreen
import com.example.aksa.presentation.onboarding.OnboardingScreen
import com.example.aksa.presentation.splash.SplashScreen
import com.example.aksa.presentation.splash.SplashViewModel
import com.example.aksa.ui.theme.AksaTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AksaTheme {

                // initiate user
                val userRepo = UserRepositoryImpl(UserPreferencesManager(this), this)
                val userUseCase = UserUseCase(userRepo)

                // initiate auth
                val authRepo = AuthRepositoryImpl()
                val authUseCase = AuthUseCase(authRepo)
                val authViewModel = AuthViewModel(authUseCase, userUseCase)

                // initiate splash & on boarding
                val onBoardingUseCase = OnBoardingUseCase(UserPreferencesManager(this))
                val splashViewModel = SplashViewModel(userUseCase, onBoardingUseCase)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavDestination.SPLASH,
                    ) {
                        composable(NavDestination.SPLASH) {
                            SplashScreen(
                                onNavigateToLogin = { navController.navigate(NavDestination.LOGIN) {
                                    popUpTo(NavDestination.SPLASH) {
                                        inclusive = true
                                    }
                                } },
                                onNavigateToHome = { navController.navigate(NavDestination.HOME) {
                                    popUpTo(NavDestination.SPLASH) {
                                        inclusive = true
                                    }
                                } },
                                onNavigateToOnBoarding = { navController.navigate(NavDestination.ONBOARDING) {
                                    popUpTo(NavDestination.SPLASH) {
                                        inclusive = true
                                    }
                                } },
                                splashViewModel = splashViewModel

                            )
                        }

                        composable(NavDestination.ONBOARDING) {
                            OnboardingScreen(
                                onFinishClick = { navController.navigate(NavDestination.LOGIN) {
                                    popUpTo(NavDestination.ONBOARDING) {
                                        inclusive = true
                                    }
                                } },
                                splashViewModel = splashViewModel
                            )
                        }

                        composable(NavDestination.LOGIN) {
                            LoginScreen(
                                onRegisterClick = { navController.navigate(NavDestination.REGISTER) },
                                onForgotPasswordClick = { navController.navigate(NavDestination.FORGOT_PASSWORD) },
                                onBackClick = { navController.popBackStack() },
                                onNavigateToHome = { navController.navigate(NavDestination.HOME) {
                                    popUpTo(NavDestination.LOGIN) {
                                        inclusive = true
                                    }
                                } },
                                authViewModel = authViewModel
                            )
                        }

                        composable(NavDestination.REGISTER) {
                            RegisterScreen(
                                onLoginClick = { navController.navigate(NavDestination.LOGIN) {
                                    popUpTo(NavDestination.REGISTER) {
                                        inclusive = true
                                    }
                                } },
                                onBackClick = { navController.popBackStack() },
                                onNavigateToLogin = { navController.navigate(NavDestination.LOGIN) {
                                    popUpTo(NavDestination.REGISTER) {
                                        inclusive = true
                                    }
                                } },
                                authViewModel = authViewModel
                            )
                        }

                        composable(NavDestination.FORGOT_PASSWORD) {
                            ForgotPasswordScreen(
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable(NavDestination.HOME) {
                            Text(text = "Home")
                        }
                    }
                }
            }
        }
    }
}
