package com.example.aksa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aksa.presentation.auth.ForgotPasswordScreen
import com.example.aksa.presentation.auth.LoginScreen
import com.example.aksa.presentation.auth.RegisterScreen
import com.example.aksa.presentation.onboarding.OnboardingScreen
import com.example.aksa.presentation.splash.SplashScreen
import com.example.aksa.ui.theme.AksaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AksaTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavDestination.SPLASH,
                    ) {
                        composable(NavDestination.SPLASH) {
                            SplashScreen(
                                onNavigateToNext = { navController.navigate(NavDestination.ONBOARDING) }
                            )
                        }

                        composable(NavDestination.ONBOARDING) {
                            OnboardingScreen(
                                onFinishClick = { navController.navigate(NavDestination.LOGIN) }
                            )
                        }

                        composable(NavDestination.LOGIN) {
                            LoginScreen(
                                onRegisterClick = { navController.navigate(NavDestination.REGISTER) },
                                onForgotPasswordClick = { navController.navigate(NavDestination.FORGOT_PASSWORD) },
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable(NavDestination.REGISTER) {
                            RegisterScreen(
                                onLoginClick = { navController.navigate(NavDestination.LOGIN) {
                                    popUpTo(NavDestination.REGISTER) {
                                        inclusive = true
                                    }
                                } },
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable(NavDestination.FORGOT_PASSWORD) {
                            ForgotPasswordScreen(
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
