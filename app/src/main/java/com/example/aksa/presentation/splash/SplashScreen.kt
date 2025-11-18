package com.example.aksa.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.common.AngularGradientShape
import com.example.aksa.ui.theme.NonWhite

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToOnBoarding: () -> Unit,
    splashViewModel: SplashViewModel
) {
    val alphaAnim = remember { Animatable(0f) }

    val splashState by splashViewModel.splashState.collectAsState()

    LaunchedEffect(Unit) {
        alphaAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    LaunchedEffect(splashState) {
        when (splashState) {
            is SplashState.NavigateToHome -> onNavigateToHome()
            is SplashState.NavigateToLogin -> onNavigateToLogin()
            is SplashState.NavigateToOnBoarding -> onNavigateToOnBoarding()
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NonWhite)
            .graphicsLayer { alpha = alphaAnim.value },
        contentAlignment = Alignment.Center
    ) {
        // background
        AngularGradientShape(
            offsetX = 175f,
            offsetY = -353f,
            width = 316f,
            height = 277f
        )

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