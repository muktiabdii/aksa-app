package com.example.aksa.presentation.auth.comps

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc80

@Composable
fun AuthButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        shape = RoundedCornerShape(30.dp),
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = Sc80,
            contentColor = NonWhite,
            disabledContainerColor = Sc80.copy(alpha = 0.6f),
            disabledContentColor = NonWhite.copy(alpha = 0.8f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp
        )
    ) {
        if (isLoading) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 4.dp, end = 8.dp),
                    strokeWidth = 2.dp
                )
                Text(
                    text = "Loading...",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_semibold))
                    )
                )
            }
        } else {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold))
                )
            )
        }
    }
}