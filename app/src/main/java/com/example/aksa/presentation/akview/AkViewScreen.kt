package com.example.aksa.presentation.akview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aksa.R
import com.example.aksa.presentation.akview.comps.AddContributionCard
import com.example.aksa.presentation.akview.comps.MuseumCard
import com.example.aksa.presentation.common.AngularGradientShape
import com.example.aksa.presentation.common.TabSelector
import com.example.aksa.presentation.common.TopBar
import com.example.aksa.ui.theme.Sc90

// Data class dummy
data class Museum(
    val name: String,
    val imageRes: Int
)

@Composable
fun AkViewScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onMuseumClick: (String) -> Unit = {},
    onAddContributionClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    // Dummy Data: List Museum Umum
    val museumList = remember {
        listOf(
            Museum("Museum Nasional", R.drawable.museum_placeholder),
            Museum("Museum Ulen Sentanu", R.drawable.museum_placeholder),
            Museum("Museum Batik", R.drawable.museum_placeholder),
            Museum("Museum Bali", R.drawable.museum_placeholder),
            Museum("Museum Yogyakarta", R.drawable.museum_placeholder),
            Museum("Museum La Galigo", R.drawable.museum_placeholder),
        )
    }

    // Dummy Data: List Kontribusi User
    val contributionList = remember {
        listOf(
            Museum("Museum Fatahillah", R.drawable.museum_placeholder)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // background
        AngularGradientShape(
            offsetX = 150f,
            offsetY = -130f,
            width = 350f,
            height = 350f
        )

        AngularGradientShape(
            offsetX = -100f,
            offsetY = 550f,
            width = 300f,
            height = 400f
        )

        Column(
            modifier = modifier.fillMaxSize()
        ) {
            // topbar
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopBar(
                    title = "AkView",
                    onBackClick = onBackClick
                )

                // visibilitas history
                if (selectedTab == 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(top = 12.dp, end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Sc90)
                            .clickable { onHistoryClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_history),
                            contentDescription = "History",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Tab Selector
                TabSelector(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                    options = listOf("Museum", "Kontribusi")
                )

                Spacer(modifier = Modifier.height(24.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    if (selectedTab == 0) {
                        // tab museum
                        items(museumList) { museum ->
                            MuseumCard(
                                name = museum.name,
                                imageRes = museum.imageRes,
                                onClick = { onMuseumClick(museum.name) }
                            )
                        }
                    } else {
                        // tab kontribusi
                        items(contributionList) { museum ->
                            MuseumCard(
                                name = museum.name,
                                imageRes = museum.imageRes,
                                onClick = { onMuseumClick(museum.name) }
                            )
                        }

                        item {
                            AddContributionCard(
                                onClick = onAddContributionClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AkViewScreenPreview() {
    AkViewScreen()
}