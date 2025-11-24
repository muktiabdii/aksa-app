package com.example.aksa.presentation.akvault

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.aksa.presentation.common.TopBar
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc80
import com.example.aksa.ui.theme.Sc100

@Composable
fun DetailMuseumAkVaultScreen(
    onBackClick: () -> Unit = {},
    onArtifactClick: (ArtifactItem) -> Unit = {},
    museumName: String = "Museum Nasional",
    museumImage: Int = R.drawable.img_museum_nasional,
    artifacts: List<ArtifactItem> = listOf(
        ArtifactItem(
            name = "Arca Ganesha",
            location = "Jawa Timur",
            image = R.drawable.img_header_forgot_password,
            museumCategory = "Museum Nasional"
        ),
        ArtifactItem(
            name = "Topeng Panji",
            location = "Jawa Tengah",
            image = R.drawable.img_header_forgot_password,
            museumCategory = "Museum Nasional"
        ),
        ArtifactItem(
            name = "Kenong Perunggu",
            location = "Jawa Barat",
            image = R.drawable.img_header_forgot_password,
            museumCategory = "Museum Nasional"
        )
    )
) {
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
                modifier = Modifier.fillMaxSize()
            ) {
                item {

                    // museum name header
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Sc100)
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = museumName,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                                color = NonWhite
                            )
                        )
                    }

                    // museum image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .clip(
                                    RoundedCornerShape(
                                        bottomStart = 100.dp,
                                        bottomEnd = 100.dp
                                    )
                                )
                                .background(Sc100)
                        )

                        Box(
                            modifier = Modifier
                                .width(260.dp)
                                .height(150.dp)
                                .align(Alignment.Center)
                                .clip(RoundedCornerShape(18.dp))
                        ) {
                            Image(
                                painter = painterResource(id = museumImage),
                                contentDescription = museumName,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // item found header
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

                items(artifacts) { artifact ->
                    Box(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        ArtifactItemCard(
                            artifact = artifact,
                            onClick = { onArtifactClick(artifact) },
                            backgroundColor = NonWhite,
                            borderColor = Sc100
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
