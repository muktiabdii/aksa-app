package com.example.aksa.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.Sc80
import com.example.aksa.ui.theme.poltawskinowyFamily

@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    titleColor: Color = Sc80,
    iconTint: Color = NonBlack,
    backgroundColor: Color = Color(0xFFFEFEFE).copy(alpha = 0.2f),
    shadowElevation: Dp = 2.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .offset(y = (-2).dp)
            .shadow(
                elevation = shadowElevation,
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
                clip = false,
                ambientColor = Color.Transparent,
                spotColor = Color.Black.copy(alpha = 0.40f)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
            )
            .padding(horizontal = 16.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = iconTint
            )
        }

        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poltawskinowyFamily,
                color = titleColor
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(
        title = "Edit Profile",
        onBackClick = {}
    )
}