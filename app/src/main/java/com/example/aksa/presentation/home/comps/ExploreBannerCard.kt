package com.example.aksa.presentation.home.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc30
import com.example.aksa.ui.theme.Sc90

@Composable
fun ExploreBannerCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Google Maps Image
            Image(
                painter = painterResource(id = R.drawable.img_gmaps),
                contentDescription = "Google Maps Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.6f
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF6D4432).copy(alpha = 0f),
                                Color(0xFF6D4432).copy(alpha = 0.63f),
                                Color(0xFF6D4432).copy(alpha = 1f)
                            ),
                            startX = 0f,
                            endX = 1200f
                        )
                    )
            )

            // === Bottom background layer (di belakang button & character) ===
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp) // ubah sesuai Figma
                    .align(Alignment.BottomCenter)
                    .background(Sc90) // warna panel bawah
            )

            // === MAIN CONTENT ===
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp, top = 10.dp, end = 17.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // LEFT side — Character Image
                Box(
                    modifier = Modifier.size(150.dp)
                ) {

                    // Character Image (di atas background)
                    Image(
                        painter = painterResource(id = R.drawable.img_explore_home),
                        contentDescription = "Explorer Character",
                        modifier = Modifier
                            .size(150.dp)
                            .align(Alignment.TopStart),
                        contentScale = ContentScale.Fit
                    )
                }

                // RIGHT side — Text & Button
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    // Title + Description
                    Column(
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        Text(
                            text = "Siap menjelajah?",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poltawskinowy_semibold)),
                                color = Color.White,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = "Jelajahi museum lewat rute yang terbentuk dari jejak pengunjung sebelumnya.",
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                                color = Sc30,
                                textAlign = TextAlign.End,
                                lineHeight = 15.sp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // === Button (di atas layer bottom background) ===
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Mulai Eksplorasi",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                                color = NonWhite
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_right_home),
                            contentDescription = "Arrow",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun sdf() {
    ExploreBannerCard(
        onClick = {}
    )
}
