package com.example.aksa.presentation.akvault.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc80


@Composable
fun DayItem(
    day: String,
    date: String,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .size(width = 40.dp, height = 60.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) {
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF632610),
                            Color(0xFFA7725B)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        listOf(Color.Transparent, Color.Transparent)
                    )
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = day,
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    color = NonWhite
                )
            )

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) {
                            Color.White
                        } else {
                            Color.Transparent
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date,
                    style = if (isSelected) {
                        TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            brush = Brush.linearGradient(
                                listOf(Color(0xFFC48C74), Color(0xFF9D282A))
                            )
                        )
                    } else {
                        TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            color = NonWhite
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun sdf() {
    DayItem(
        day = "Sen",
        date = "01",
        isSelected = true
    )
}