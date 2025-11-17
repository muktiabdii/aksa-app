package com.example.aksa.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import kotlinx.coroutines.delay
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import com.example.aksa.presentation.splash.comps.AngularGradientShape
import com.example.aksa.ui.theme.NonWhite

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToOnBoarding: () -> Unit = {},
    splashViewModel: SplashViewModel
) {
    // animasi alpha
    val alphaAnim = remember { Animatable(0f) }

    val isOnBoardingShown by splashViewModel.isOnBoardingShown().collectAsState(initial = false)
    val userUid by splashViewModel.getUserUidFlow().collectAsState(initial = null)

    LaunchedEffect(Unit) {
        // durasi fade in
        alphaAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 600)
        )

        delay(1000)
        // kalau onboarding belum ditampilkan
        if (!isOnBoardingShown) {
            onNavigateToOnBoarding()
        }

        else {

            // kalau sudah onboarding tapi belum login
            if (userUid.isNullOrEmpty()) {
                onNavigateToLogin()
            }

            // kalau sudah onboarding dan sudah login
            else {
                splashViewModel.loadUser(userUid!!)
                onNavigateToHome()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NonWhite)
            .graphicsLayer { alpha = alphaAnim.value },
        contentAlignment = Alignment.Center
    ) {

        // shape
        AngularGradientShape(
            offsetX = 175f,
            offsetY = -353f,
            width = 316f,
            height = 277f
        )

        // shape
        AngularGradientShape(
            offsetX = -244f,
            offsetY = 88f,
            width = 316f,
            height = 277f
        )

        // text
        Text(
            text = "Aksa",
            style = TextStyle(
                fontSize = 84.sp,
                fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                color = Color(0xFF502F21),
                letterSpacing = 2.sp
            )
        )
    }
}
