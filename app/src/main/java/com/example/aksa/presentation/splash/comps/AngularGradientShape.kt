package com.example.aksa.presentation.splash.comps

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SweepGradientShader
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun AngularGradientShape(
    offsetX: Float,
    offsetY: Float,
    width: Float,
    height: Float
) {
    val composeRenderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val androidBlur = RenderEffect.createBlurEffect(
            280f,
            280f,
            Shader.TileMode.CLAMP
        )
        androidBlur.asComposeRenderEffect()
    } else null

    Box(
        modifier = Modifier
            .offset(x = offsetX.dp, y = offsetY.dp)
            .size(width.dp, height.dp)
            .graphicsLayer {
                renderEffect = composeRenderEffect
            }
            .drawBehind {
                val sweepShader = SweepGradientShader(
                    center = center,
                    colors = listOf(
                        Color(0xFFA7725B).copy(alpha = 0.7f),
                        Color(0xFF632610).copy(alpha = 0.7f)
                    ),
                    colorStops = listOf(0.32f, 1f)
                )

                drawCircle(
                    brush = ShaderBrush(sweepShader),
                    radius = size.minDimension / 2f,
                    center = center
                )
            }
    )
}
