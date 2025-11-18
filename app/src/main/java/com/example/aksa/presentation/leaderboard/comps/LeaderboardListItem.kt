package com.example.aksa.presentation.leaderboard.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.Sc80
import com.example.aksa.ui.theme.poltawskinowyFamily

@Composable
fun LeaderboardListItem(
    user: LeaderboardUser,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = Sc80,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = user.rank.toString(),
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = poltawskinowyFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.width(40.dp)
        )

        Text(
            text = user.name,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                color = Color.White
            ),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = user.points.toString(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                color = Color.White
            )
        )
    }
}