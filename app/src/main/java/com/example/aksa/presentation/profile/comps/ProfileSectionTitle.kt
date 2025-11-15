package com.example.aksa.presentation.profile.comps

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.ui.theme.AksaTheme
import com.example.aksa.ui.theme.NonBlack

@Composable
fun ProfileSectionTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = NonBlack,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionTitlePreview() {
    AksaTheme {
        ProfileSectionTitle(title = "Pengaturan")
    }
}