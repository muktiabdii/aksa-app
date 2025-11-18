package com.example.aksa.presentation.leaderboard.comps

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.example.aksa.R
import com.example.aksa.ui.theme.Sc100
import com.example.aksa.ui.theme.Sc90
import com.example.aksa.ui.theme.poltawskinowyFamily

data class LeaderboardUser(
    val rank: Int,
    val name: String,
    val points: Int,
    val imageUrl: String
)

@Composable
fun PodiumSection(
    topThree: List<LeaderboardUser>,
    modifier: Modifier = Modifier
) {
    if (topThree.size < 3) return

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        // Juara 2
        PodiumItem(
            user = topThree[1],
            height = 180.dp,
            imageSize = 80.dp,
            ovalHeight = 25.dp,
            modifier = Modifier.weight(1f).padding(end = 4.dp)
        )

        // Juara 1
        PodiumItem(
            user = topThree[0],
            height = 220.dp,
            imageSize = 100.dp,
            isWinner = true,
            ovalHeight = 30.dp,
            modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
        )

        // Juara 3
        PodiumItem(
            user = topThree[2],
            height = 160.dp,
            imageSize = 80.dp,
            ovalHeight = 25.dp,
            modifier = Modifier.weight(1f).padding(start = 4.dp)
        )
    }
}

@Composable
fun PodiumItem(
    user: LeaderboardUser,
    height: Dp,
    imageSize: Dp,
    ovalHeight: Dp,
    isWinner: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // PROFILE PICTURE
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .offset(y = 15.dp)
                .zIndex(2f)
        ) {
            // BAYANGAN OVAL (Shadow)
            Canvas(
                modifier = Modifier
                    .width(imageSize * 0.6f)
                    .height(6.dp)
                    .offset(y = (imageSize / 2) + 2.dp)
            ) {
                drawOval(
                    color = Color.Black.copy(alpha = 0.2f),
                    size = size
                )
            }

            // Foto Profil Utama
            AsyncImage(
                model = user.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(imageSize)
                    .clip(CircleShape)
                    .border(3.dp, Color.White, CircleShape)
            )
            if (isWinner) {
            }
        }

        // PODIUM STRUCTURE
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            val bodyColor = Sc100
            val topOvalColor = Color(0xFFF19A50)

            // OVAL ATAS
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ovalHeight)
                    .zIndex(2f)
            ) {
                drawOval(
                    color = topOvalColor,
                    size = size
                )
            }

            // BODY UTAMA + OVAL BAWAH
            Canvas(
                modifier = Modifier
                    .padding(top = ovalHeight / 2)
                    .fillMaxWidth()
                    .height(height + (ovalHeight / 2))
                    .zIndex(1f)
            ) {
                val canvasWidth = size.width
                val ovalHeightPx = ovalHeight.toPx()
                val straightBodyHeight = size.height - (ovalHeightPx / 2)

                // OVAL BAWAH
                drawOval(
                    color = bodyColor,
                    topLeft = Offset(0f, size.height - ovalHeightPx),
                    size = Size(canvasWidth, ovalHeightPx)
                )

                // BATANG PERSEGI
                drawRect(
                    color = bodyColor,
                    topLeft = Offset(0f, 0f),
                    size = Size(canvasWidth, straightBodyHeight)
                )
            }

            // KONTEN TEKS
            Column(
                modifier = Modifier
                    .padding(top = ovalHeight + 24.dp)
                    .zIndex(3f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = user.rank.toString(),
                    style = TextStyle(
                        fontSize = if (isWinner) 48.sp else 36.sp,
                        fontFamily = poltawskinowyFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = user.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        color = Color.White
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = user.points.toString(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Color.White.copy(alpha = 0.8f)
                    )
                )
            }
        }
    }
}