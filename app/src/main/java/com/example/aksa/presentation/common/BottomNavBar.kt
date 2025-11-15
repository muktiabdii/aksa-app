package com.example.aksa.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc30
import com.example.aksa.ui.theme.Sc90
import com.example.aksa.ui.theme.nunitoFamily

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val navItems = listOf(
        "Home" to R.drawable.ic_home,
        "Report" to R.drawable.ic_report,
        "Profile" to R.drawable.ic_profile
    )

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Sc90,
        contentColor = Sc30,
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .padding(horizontal = 40.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .background(color = NonWhite)
            )
        },
        divider = {}
    ) {
        navItems.forEachIndexed { index, (title, iconResId) ->
            Tab(
                selected = (selectedTabIndex == index),
                onClick = { selectedTabIndex = index },
                text = {
                    Text(
                        text = title,
                        fontFamily = nunitoFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = title
                    )
                },
                modifier = Modifier.padding(vertical = 4.dp),
                selectedContentColor = NonWhite,
                unselectedContentColor = Sc30
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar()
}