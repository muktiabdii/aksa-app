package com.example.aksa.presentation.leaderboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.common.AngularGradientShape
import com.example.aksa.presentation.common.TopBar
import com.example.aksa.presentation.leaderboard.comps.LeaderboardListItem
import com.example.aksa.presentation.common.TabSelector
import com.example.aksa.presentation.leaderboard.comps.LeaderboardUser
import com.example.aksa.presentation.leaderboard.comps.PodiumSection
import com.example.aksa.ui.theme.NonBlack

@Composable
fun LeaderboardScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val dummyUsers = remember {
        listOf(
            LeaderboardUser(1, "angkasaksa", 6955, "https://via.placeholder.com/150"),
            LeaderboardUser(2, "linoo_", 6880, "https://via.placeholder.com/150"),
            LeaderboardUser(3, "cikiluy", 6880, "https://via.placeholder.com/150"),
            LeaderboardUser(4, "exploreheritage", 6449, ""),
            LeaderboardUser(5, "digitourism", 6320, ""),
            LeaderboardUser(6, "kulturraya", 6210, ""),
        )
    }

    var selectedTab by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
            TopBar(
                title = "Leaderboard",
                onBackClick = onBackClick
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    TabSelector(
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it },
                        modifier = Modifier.width(280.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    PodiumSection(
                        topThree = dummyUsers.take(3)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                items(dummyUsers.drop(3)) { user ->
                    LeaderboardListItem(user = user)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Text(
                        text = "View All",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            color = NonBlack,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LeaderboardScreenPreview() {
    LeaderboardScreen()
}