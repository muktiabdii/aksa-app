package com.example.aksa.presentation.akvault

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.common.TopBar
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc10
import com.example.aksa.ui.theme.Sc100
import com.example.aksa.ui.theme.Sc80

@Composable
fun DetailItemAkVault(
    onBackClick: () -> Unit = {},
    artifactName: String = "Arca Ganesha",
    museumName: String = "Museum Nasional",
    period: String = "Abad ke 13 - 14 M",
    location: String = "Jawa Timur",
    description: String = "Arca Ganesha merupakan representasi Dewa Ganesha dalam agama Hindu yang dikenal sebagai Dewa Pengetahuan, Pelindung, dan Penghalau Rintangan. Arca ini umumnya digambarkan duduk dalam posisi lalitasana dengan atribut khas seperti kepala gajah, perut buncit, dan empat tangan yang menunjukkan kuatnya pengaruh Hindu-Siwa pada masa kerajaan-kerajaan klasik Jawa Timur. Arca ini biasanya ditempatkan di dalam candi sebagai simbol kebijaksanaan dan perlindungan spiritual.",
    artifactImage: Int = R.drawable.ic_facebook
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NonWhite)
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                // header museum name
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

                // artifact image
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
                            .size(170.dp)
                            .align(Alignment.Center)
                            .clip(CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = artifactImage),
                            contentDescription = artifactName,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                // artifact name
                Box(
                    modifier = Modifier
                        .padding(horizontal = 80.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Sc100)
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = artifactName,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poltawskinowy_bold)),
                            color = Sc10,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // period and location
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    // period chip
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        color = NonWhite,
                        border = BorderStroke(1.dp, Sc100)
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 10.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_map),
                                contentDescription = "Period",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = period,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                                    color = Sc100
                                )
                            )
                        }
                    }

                    // location chip
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        color = NonWhite,
                        border = BorderStroke(1.dp, Sc100)
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 10.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_location_2),
                                contentDescription = "Location",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = location,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                                    color = Sc100
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // information section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                ) {

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Sc100)
                    )

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Informasi Penemuan",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                color = Sc10
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = description,
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                                color = Sc10,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Justify
                            )
                        )

                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }
        }
    }
}