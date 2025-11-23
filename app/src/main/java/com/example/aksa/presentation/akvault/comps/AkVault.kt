package com.example.aksa.presentation.akvault.comps

data class Museum(
    val name: String,
    val image: Int
)

data class ArtifactItem(
    val name: String,
    val location: String,
    val image: Int,
    val museumCategory: String
)

data class DayData(
    val day: String,
    val date: String,
    val isSelected: Boolean
)
