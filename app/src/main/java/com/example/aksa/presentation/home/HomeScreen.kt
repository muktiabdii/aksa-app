package com.example.aksa.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.akvault.comps.ArtifactItem
import com.example.aksa.presentation.akvault.comps.ArtifactItemCard
import com.example.aksa.presentation.home.comps.*
import com.example.aksa.ui.theme.*

@Composable
fun HomeScreen(
    onFeatureClick: (FeatureItem) -> Unit = {},
    onSeeAllArticle: () -> Unit = {},
    onArticleClick: (NewsArticle) -> Unit = {},
    onMuseumClick: (Museum) -> Unit = {},
    onExploreClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSearchChange: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Sc10)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            // header
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {

                    // background image
                    Image(
                        painter = painterResource(id = R.drawable.img_header_home),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-40).dp),
                        contentScale = ContentScale.Crop
                    )

                    // content header
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(0.35f)
                            ) {
                                Text(
                                    text = "Selamat Datang, Angkasa!",
                                    style = TextStyle(
                                        fontSize = 21.sp,
                                        fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                                        color = Sc10
                                    )
                                )
                                Text(
                                    text = "Siapkah kamu menjelajahi dunia hari ini?",
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                                        color = Sc10
                                    )
                                )
                            }

                            IconButton(
                                onClick = onProfileClick,
                                modifier = Modifier.size(50.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_notification),
                                    contentDescription = "Profile",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // search bar
                        SearchBar(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onValueChange = onSearchChange
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            // monthly summary card section
            item {
                MonthlySummaryCard(
                    totalPoints = 842,
                    questCount = 100,
                    visitCount = 10,
                    progressPercentage = 42,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            // feature card section
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(featureItem) { feature ->
                        FeatureCard(
                            feature = feature,
                            onClick = { onFeatureClick(feature) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
            }

            // article section
            item {

                // article section title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 17.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ayo baca artikel hari ini",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                            color = Sc100
                        )
                    )

                    IconButton(onClick = onSeeAllArticle) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_right_article),
                            contentDescription = "See All",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // article card
            items(getSampleArticles()) { article ->
                ArticleCard(
                    article = article,
                    onClick = { onArticleClick(article) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // explore section
            item {

                // explore section title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 17.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Rencanakan Rute Eksplorasimu!",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                            color = Sc100
                        )
                    )
                }
            }

            // explore banner
            item {
                ExploreBannerCard(
                    onClick = onExploreClick,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // artifact section
            item {

                // artifact section title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 17.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Penemuan",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                            color = Sc100
                        )
                    )
                }
            }

            // artifact card
            items(getSampleArtifacts()) { artifact ->
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    ArtifactItemCard(
                        artifact = artifact,
                        backgroundColor = NonWhite,
                        borderColor = Sc100,
                        onClick = {  }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            // museum section
            item {

                // museum section title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 17.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mulai Berpetualang di Museum",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                            color = Sc100
                        )
                    )
                }
            }

            // museum card
            fun <T> List<T>.chunkedPairs(): List<List<T>> {
                return this.chunked(2)
            }

            item {
                val museums = getSampleMuseums().chunkedPairs()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    museums.forEach { row ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            MuseumCardHome(
                                museum = row[0],
                                onClick = { onMuseumClick(row[0]) },
                                modifier = Modifier.weight(1f)
                            )

                            if (row.size > 1) {
                                MuseumCardHome(
                                    museum = row[1],
                                    onClick = { onMuseumClick(row[1]) },
                                    modifier = Modifier.weight(1f)
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

fun getSampleArticles(): List<NewsArticle> {
    return listOf(
        NewsArticle(
            title = "5 Artefak Paling Banyak Dipindai Minggu Ini",
            description = "Lihat kenapa artefak ini sering dikunjungi.",
            date = "02 Feb 2025",
            image = R.drawable.img_article_1
        ),
        NewsArticle(
            title = "Pameran Koleksi Langka Dibuka Minggu Ini",
            description = "Artefak spesial tampil terbatas sepekan.",
            date = "07 Feb 2025",
            image = R.drawable.img_article_2
        )
    )
}

fun getSampleMuseums(): List<Museum> {
    return listOf(
        Museum(
            name = "Museum Nasional",
            image = R.drawable.img_museum_nasional
        ),
        Museum(
            name = "Museum Ulen Sentanu",
            image = R.drawable.img_museum_ulen_sentanu
        ),
        Museum(
            name = "Museum Batik",
            image = R.drawable.img_museum_batik
        ),
        Museum(
            name = "Museum Bali",
            image = R.drawable.img_museum_bali
        )
    )
}

fun getSampleArtifacts(): List<ArtifactItem> {
    return listOf(
        ArtifactItem(
            name = "Arca Ganesha",
            location = "Museum Nasional",
            image = R.drawable.img_artifact,
            museumCategory = "Nasional"
        ),
        ArtifactItem(
            name = "Perunggu Bali",
            location = "Museum Bali",
            image = R.drawable.img_artifact,
            museumCategory = "Bali"
        )
    )
}
