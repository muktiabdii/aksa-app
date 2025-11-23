package com.example.aksa.presentation.akview.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.Sc90

@Composable
fun AddContributionCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.LightGray.copy(alpha = 0.5f), // Atas agak terang
                        Sc90.copy(alpha = 0.8f)   // Bawah gelap (Coklat Tua)
                    )
                )
            )
            .clickable(onClick = onClick)
    ) {
        // Ikon Plus di Tengah
        Icon(
            painter = painterResource(id = R.drawable.ic_add), // Pastikan ic_add ada
            contentDescription = "Add Museum",
            tint = NonBlack, // Warna ikon gelap
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp)
                .padding(bottom = 16.dp) // Geser sedikit ke atas agar imbang dengan teks
        )

        // Teks di Bawah
        Text(
            text = "Tambahkan Museum",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun AddContributionCardPreview() {
    AddContributionCard(onClick = {})
}