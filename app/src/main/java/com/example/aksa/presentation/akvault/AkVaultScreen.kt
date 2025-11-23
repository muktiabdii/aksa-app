package com.example.aksa.presentation.akvault

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.akvault.comps.ArtifactItem
import com.example.aksa.presentation.akvault.comps.ArtifactItemCard
import com.example.aksa.presentation.akvault.comps.DayData
import com.example.aksa.presentation.akvault.comps.DayItem
import com.example.aksa.presentation.akvault.comps.Museum
import com.example.aksa.presentation.akvault.comps.MuseumCard
import com.example.aksa.presentation.akvault.comps.MuseumSection
import com.example.aksa.presentation.common.TopBar
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc100

@Composable
fun AkVaultScreen(
    onBackClick: () -> Unit = {},
    onMuseumClick: (Museum) -> Unit = {},
    onArtifactClick: (ArtifactItem) -> Unit = {}
) {
    val museums = listOf(
        Museum("Museum Nasional", R.drawable.img_header_forgot_password),
        Museum("Museum Batik", R.drawable.img_header_forgot_password)
    )

    val artifactsNasional = listOf(
        ArtifactItem(
            "Arca Ganesha",
            "Lihat detail alamatnya",
            R.drawable.img_header_forgot_password,
            "Museum Nasional"
        ),
        ArtifactItem(
            "Arca Bhairawa",
            "Lihat detail alamatnya",
            R.drawable.img_header_forgot_password,
            "Museum Nasional"
        ),
        ArtifactItem(
            "Arca Prajnaparamita",
            "Lihat detail alamatnya",
            R.drawable.img_header_forgot_password,
            "Museum Nasional"
        )
    )

    val artifactsBatik = listOf(
        ArtifactItem(
            "Canting",
            "Lihat detail alamatnya",
            R.drawable.img_header_forgot_password,
            "Museum Batik"
        ),
        ArtifactItem(
            "Batik Kelengan",
            "Lihat detail alamatnya",
            R.drawable.img_header_forgot_password,
            "Museum Batik"
        ),
        ArtifactItem(
            "Wayang Beber",
            "Lihat detail alamatnya",
            R.drawable.img_header_forgot_password,
            "Museum Batik"
        )
    )

    val days = listOf(
        DayData("Sen", "01", false),
        DayData("Sel", "02", false),
        DayData("Rab", "03", true),
        DayData("Kam", "04", false),
        DayData("Jum", "05", false),
        DayData("Sab", "06", false),
        DayData("Min", "07", false)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(
                title = "AkVault",
                onBackClick = onBackClick,
                titleColor = NonBlack,
                iconTint = NonBlack,
                backgroundColor = Color.White,
                shadowElevation = 4.dp
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // date header
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                            .background(Sc100)
                            .padding(top = 16.dp, bottom = 28.dp)
                    ) {

                        // title
                        Text(
                            text = "Riwajat Kunjungan & Koleksi Item",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                                color = NonWhite,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        )

                        // days item row
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp)
                        ) {
                            items(days) { day ->
                                DayItem(
                                    day = day.day,
                                    date = day.date,
                                    isSelected = day.isSelected
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }

                // museum yang dikunjungi
                item {
                    Text(
                        text = "Museum yang dikunjungi",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                            color = Sc100
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        museums.forEach { museum ->
                            MuseumCard(
                                museum = museum,
                                onClick = { onMuseumClick(museum) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }

                // item yang ditemukan
                item {
                    Text(
                        text = "Item ditemukan",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                            color = Sc100
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        MuseumSection(
                            title = "Museum Nasional",
                            items = artifactsNasional,
                            onArtifactClick = onArtifactClick,
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        MuseumSection(
                            title = "Museum Batik",
                            items = artifactsBatik,
                            onArtifactClick = onArtifactClick
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
