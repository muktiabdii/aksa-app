package com.example.aksa.presentation.auth.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import com.example.aksa.R
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.Pr10

@Composable
fun AuthSocialButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: Int? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp),
        shape = RoundedCornerShape(30.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Pr10,
            contentColor = NonBlack,
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold))
                )
            )
        }
    }
}