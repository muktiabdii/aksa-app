package com.example.aksa

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aksa.domain.usecase.AuthUseCase
import com.example.aksa.domain.usecase.OnBoardingUseCase
import com.example.aksa.domain.usecase.UserUseCase
import com.example.aksa.presentation.akvault.AkVaultScreen
import com.example.aksa.presentation.akvault.DetailItemAkVault
import com.example.aksa.presentation.akvault.DetailMuseumAkVaultScreen
import com.example.aksa.presentation.auth.AuthViewModel
import com.example.aksa.presentation.auth.AuthViewModelFactory
import com.example.aksa.presentation.auth.ForgotPasswordScreen
import com.example.aksa.presentation.auth.LoginScreen
import com.example.aksa.presentation.auth.RegisterScreen
import com.example.aksa.presentation.common.BottomNavBar
import com.example.aksa.presentation.home.HomeScreen
import com.example.aksa.presentation.onboarding.OnboardingScreen
import com.example.aksa.presentation.profile.ProfileScreen
import com.example.aksa.presentation.report.ReportScreen
import com.example.aksa.presentation.splash.SplashScreen
import com.example.aksa.presentation.splash.SplashViewModel
import com.example.aksa.presentation.splash.SplashViewModelFactory

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    userUseCase: UserUseCase,
    authUseCase: AuthUseCase,
    onBoardingUseCase: OnBoardingUseCase
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    // rute yang ada bottomnavbar
    val bottomNavRoutes = listOf(
        NavDestination.HOME,
        NavDestination.REPORT,
        NavDestination.PROFILE
    )

    val showBottomBar = currentRoute in bottomNavRoutes

    val authViewModelFactory = AuthViewModelFactory(authUseCase, userUseCase)
    val splashViewModelFactory = SplashViewModelFactory(userUseCase, onBoardingUseCase)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(NavDestination.HOME) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavDestination.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {
            // SPLASH & ONBOARDING
            composable(NavDestination.SPLASH) {
                val splashViewModel: SplashViewModel = viewModel(factory = splashViewModelFactory)
                SplashScreen(
                    onNavigateToLogin = {
                        navController.navigate(NavDestination.LOGIN) {
                            popUpTo(NavDestination.SPLASH) { inclusive = true }
                        }
                    },
                    onNavigateToHome = {
                        navController.navigate(NavDestination.HOME) {
                            popUpTo(NavDestination.SPLASH) { inclusive = true }
                        }
                    },
                    onNavigateToOnBoarding = {
                        navController.navigate(NavDestination.ONBOARDING) {
                            popUpTo(NavDestination.SPLASH) { inclusive = true }
                        }
                    },
                    splashViewModel = splashViewModel
                )
            }

            composable(NavDestination.ONBOARDING) {
                val splashViewModel: SplashViewModel = viewModel(factory = splashViewModelFactory)
                OnboardingScreen(
                    onFinishClick = {
                        navController.navigate(NavDestination.LOGIN) {
                            popUpTo(NavDestination.ONBOARDING) { inclusive = true }
                        }
                    },
                    splashViewModel = splashViewModel
                )
            }

            // AUTHENTICATION
            composable(NavDestination.LOGIN) {
                val authViewModel: AuthViewModel = viewModel(factory = authViewModelFactory)
                LoginScreen(
                    onRegisterClick = { navController.navigate(NavDestination.REGISTER) },
                    onForgotPasswordClick = { navController.navigate(NavDestination.FORGOT_PASSWORD) },
                    onBackClick = { navController.popBackStack() },
                    onNavigateToHome = {
                        navController.navigate(NavDestination.HOME) {
                            popUpTo(NavDestination.LOGIN) { inclusive = true }
                        }
                    },
                    authViewModel = authViewModel
                )
            }

            composable(NavDestination.REGISTER) {
                val authViewModel: AuthViewModel = viewModel(factory = authViewModelFactory)
                RegisterScreen(
                    onLoginClick = {
                        navController.navigate(NavDestination.LOGIN) {
                            popUpTo(NavDestination.REGISTER) { inclusive = true }
                        }
                    },
                    onBackClick = { navController.popBackStack() },
                    onNavigateToLogin = {
                        navController.navigate(NavDestination.LOGIN) {
                            popUpTo(NavDestination.REGISTER) { inclusive = true }
                        }
                    },
                    authViewModel = authViewModel
                )
            }

            composable(NavDestination.FORGOT_PASSWORD) {
                ForgotPasswordScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // HOME, REPORT, PROFILE
            composable(NavDestination.HOME) {
                HomeScreen(
                    onProfileClick = {  },
                    onFeatureClick = {  },
                    onArticleClick = {  },
                    onMuseumClick = {  },
                    onExploreClick = {  },
                    onSearchChange = {  }
                )
            }

            composable(NavDestination.REPORT) {
                ReportScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(NavDestination.PROFILE) {
                ProfileScreen(
                    profileImageRes = R.drawable.profile_picture,
                    onLogout = {
                        navController.navigate(NavDestination.LOGIN) {
                            popUpTo(NavDestination.HOME) { inclusive = true }
                        }
                    }
                )
            }

            // AKVAULT
            composable (NavDestination.AKVAULT ) {
                AkVaultScreen(
                    onBackClick = { navController.popBackStack() },
                    onMuseumClick = { navController.navigate(NavDestination.DETAIL_MUSEUM_AKVAULT) },
                    onArtifactClick = { navController.navigate(NavDestination.DETAIL_ITEM_AKVAULT) }
                )
            }

            composable(NavDestination.DETAIL_ITEM_AKVAULT) {
                DetailItemAkVault(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(NavDestination.DETAIL_MUSEUM_AKVAULT) {
                DetailMuseumAkVaultScreen(
                    onBackClick = { navController.popBackStack() },
                    onArtifactClick = { navController.navigate(NavDestination.DETAIL_ITEM_AKVAULT) }
                )
            }
        }
    }
}