package com.example.aksa.presentation.akview.museum

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.aksa.R
import com.example.aksa.presentation.akview.comps.PanoramaViewer
import com.example.aksa.presentation.akview.comps.ViewerControls
import com.example.aksa.presentation.common.AngularGradientShape
import com.example.aksa.presentation.common.TopBar
import com.example.aksa.ui.theme.Sc100
import com.example.aksa.ui.theme.Sc80
import com.example.aksa.ui.theme.Sc90

@Composable
fun ViewVirtualScreen(
    modifier: Modifier = Modifier,
    museumName: String = "Museum Batik",
    onBackClick: () -> Unit = {},
    initialLockedState: Boolean = true
) {
    var isLocked by remember { mutableStateOf(initialLockedState) }
    // State untuk Mode Full Screen
    var isFullScreen by remember { mutableStateOf(false) }

    // Tangani tombol back HP saat mode fullscreen
    BackHandler(enabled = isFullScreen) {
        isFullScreen = false
    }

    val headerTitle = if (isLocked) "Pre-View Virtual $museumName" else "Virtual $museumName"

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. Background (Hanya tampil jika TIDAK fullscreen)
        if (!isFullScreen) {
            AngularGradientShape(offsetX = 150f, offsetY = -130f, width = 350f, height = 350f)
            AngularGradientShape(offsetX = -100f, offsetY = 550f, width = 300f, height = 400f)
        }

        // 2. Layout Utama
        // Jika Fullscreen, kita hanya tampilkan Viewer di atas segalanya
        if (isFullScreen) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black).zIndex(10f)) {
                // Viewer Fullscreen
                PanoramaViewer(
                    imageRes = R.drawable.dummy360,
                    isLocked = false, // Pasti unlocked kalau masuk fullscreen
                    modifier = Modifier.fillMaxSize()
                )

                // Controls (Zoom, dll)
                ViewerControls(
                    onZoomIn = {}, // Implementasi zoom bisa ditambahkan nanti
                    onZoomOut = {},
                    onFullscreen = { isFullScreen = false }, // Tombol ini jadi "Exit Fullscreen"
                    modifier = Modifier.align(Alignment.TopEnd) // Pindah ke kanan atas
                )
            }
        } else {
            // Layout Normal (Card Mode)
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                // Top Bar
                Box(modifier = Modifier.fillMaxWidth()) {
                    TopBar(title = "AkView", onBackClick = onBackClick)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(20.dp)) // Radius diubah ke 24.dp
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF632610), // Warna Atas
                                        Color(0xFFA7725B)  // Warna Bawah
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = headerTitle,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 360 Viewer Card
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.LightGray)
                    ) {
                        PanoramaViewer(
                            imageRes = R.drawable.dummy360,
                            isLocked = isLocked,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Icon 360 (Hiasan)
                        Icon(
                            painter = painterResource(id = R.drawable.ic_360_indicator),
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(48.dp).align(Alignment.Center)
                        )

                        // Controls Overlay (Muncul jika unlocked)
                        if (!isLocked) {
                            ViewerControls(
                                onZoomIn = {},
                                onZoomOut = {},
                                onFullscreen = { isFullScreen = true }, // Trigger Fullscreen
                                modifier = Modifier.align(Alignment.TopStart)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Bottom Button
                    Button(
                        onClick = {
                            if (isLocked) {
                                isLocked = false // Unlock
                            } else {
                                isFullScreen = true // Masuk Fullscreen
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(24.dp), // Radius diubah ke 20.dp
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Sc100 // Warna diubah ke Sc100
                        )
                    ) {
                        Text(
                            text = if (isLocked) "Buka Akses (Rp 5.000)" else "Lihat Full-View",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewVirtualScreenPreview() {
    ViewVirtualScreen(initialLockedState = true)
}