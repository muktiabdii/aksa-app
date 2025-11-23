package com.example.aksa.presentation.akview.comps

import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import com.panoramagl.PLImage
import com.panoramagl.PLManager
import com.panoramagl.PLSphericalPanorama
import com.panoramagl.utils.PLUtils

@Composable
fun PanoramaViewer(
    imageRes: Int,
    isLocked: Boolean,
    modifier: Modifier = Modifier
) {
    val isInPreview = LocalInspectionMode.current

    if (isInPreview) {
        // --- MODE PREVIEW (Gambar Diam) ---
        Box(
            modifier = modifier.background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            if (isLocked) Text("LOCKED PREVIEW", color = Color.White)
        }
    } else {
        // --- MODE ASLI (PanoramaGL) ---
        val context = LocalContext.current
        val plManager = remember { PLManager(context) }

        AndroidView(
            factory = { ctx ->
                val container = object : FrameLayout(ctx) {

                    // TRIK 1: Intercept sentuhan agar pasti masuk ke sini
                    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
                        return true
                    }

                    // TRIK 2: Paksa return true agar swipe terbaca
                    override fun onTouchEvent(event: MotionEvent): Boolean {
                        if (isLocked) return true // Kalau dikunci, telan sentuhan (diam)

                        // Oper sentuhan ke PanoramaGL
                        plManager.onTouchEvent(event)

                        // PENTING: Harus return true!
                        // Agar Android terus mengirim event 'MOVE' (geser) ke sini.
                        return true
                    }
                }

                // Setup Manager
                plManager.setContentView(container)
                plManager.onCreate()

                // Konfigurasi agar enak di-swipe
                plManager.isAccelerometerEnabled = false // Matikan sensor gerak (opsional, biar ga pusing)
                plManager.isInertiaEnabled = true        // Biar ada efek 'meluncur' pas dilepas
                plManager.isZoomEnabled = true           // Aktifkan pinch-to-zoom

                try {
                    // Load Gambar
                    val panorama = PLSphericalPanorama()
                    panorama.camera.lookAt(0.0f, 0.0f)

                    val bitmap = PLUtils.getBitmap(ctx, imageRes)
                    val plImage = PLImage(bitmap, false)
                    panorama.setImage(plImage)

                    plManager.panorama = panorama
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                container
            },
            update = {
                // Update logika kunci secara real-time
                // Kita matikan fitur scrolling di level manager
                plManager.isScrollingEnabled = !isLocked
                plManager.isZoomEnabled = !isLocked
                plManager.isInertiaEnabled = !isLocked
            },
            modifier = modifier
        )

        // Lifecycle wajib agar tidak memory leak
        DisposableEffect(Unit) {
            plManager.onResume()
            onDispose {
                plManager.onPause()
                // plManager.onDestroy() // Hati-hati, kadang bikin crash di Compose kalau di-destroy paksa
            }
        }
    }
}