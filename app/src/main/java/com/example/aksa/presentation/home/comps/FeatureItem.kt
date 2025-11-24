package com.example.aksa.presentation.home.comps

import com.example.aksa.R

data class FeatureItem(
    val title: String,
    val description: String,
    val image: Int
)

val featureItem = listOf(
    FeatureItem(
        title = "AkRoute",
        description = "Lacak rute budaya terdekat dan temukan museum di sekitarmu",
        image = R.drawable.img_akroute
    ),
    FeatureItem(
        title = "AkVault",
        description = "Simpan artefak budaya hasil kontribusimu di satu tempat",
        image = R.drawable.img_akvault
    ),
    FeatureItem(
        title = "AkView",
        description = "Jelajahi museum virtual 360 dan kontribusi museum dari pengguna",
        image = R.drawable.img_akview
    ),
    FeatureItem(
        title = "AkQuest",
        description = "Selesaikan tantangan budaya dan dapatkan reward menarik.",
        image = R.drawable.img_akquest
    )
)
