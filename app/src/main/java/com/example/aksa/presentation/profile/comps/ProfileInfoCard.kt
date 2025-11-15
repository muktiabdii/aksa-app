package com.example.aksa.presentation.profile.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R

@Composable
fun ProfileInfoCard(
    name: String = "My Kisah",
    username: String = "@avvvv",
    profileImageRes: Int,
    onEditClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = profileImageRes),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4A4A4A)
            )
            Text(
                text = username,
                fontSize = 14.sp,
                color = Color(0xFF9B9B9B)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "Share",
            modifier = Modifier
                .size(24.dp)
                .clickable { onShareClick() },
            tint = Color(0xFF4A4A4A)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_pencil),
            contentDescription = "Edit",
            modifier = Modifier
                .size(24.dp)
                .clickable { onEditClick() },
            tint = Color(0xFF4A4A4A)
        )
    }
}
