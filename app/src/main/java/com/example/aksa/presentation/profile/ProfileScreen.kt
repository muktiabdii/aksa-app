package com.example.aksa.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aksa.presentation.common.BottomNavBar
import com.example.aksa.presentation.profile.comps.*
import com.example.aksa.ui.theme.AksaTheme
import com.example.aksa.R
import com.example.aksa.ui.theme.Sc10

@Composable
fun ProfileScreen(
    profileImageRes: Int,
    onToggleNotification: (Boolean) -> Unit = {},
    onNavigateToFAQ: () -> Unit = {},
    onNavigateToAbout: () -> Unit = {},
    onNavigateToLanguage: () -> Unit = {},
    onNavigateToSecurity: () -> Unit = {},
    onNavigateToDeleteAccount: () -> Unit = {},
    onEditProfile: () -> Unit = {},
    onShareProfile: () -> Unit = {},
    onLogout: () -> Unit = {},
    onToggleNightMode: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Sc10)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Header
            item {
                ProfileHeader()
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Profile Info
            item {
                ProfileInfoCard(
                    profileImageRes = profileImageRes,
                    onEditClick = onEditProfile,
                    onShareClick = onShareProfile
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Stats
            item {
                StatsCards()
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Arsip Pribadi
            item {
                ArchiveCard()
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Settings Menu
            item {
                SettingsMenu(
                    onNotificationToggle = onToggleNotification,
                    onFaqClick = onNavigateToFAQ,
                    onAboutClick = onNavigateToAbout,
                    onLanguageClick = onNavigateToLanguage,
                    onSecurityClick = onNavigateToSecurity,
                    onDeleteAccountClick = onNavigateToDeleteAccount,
                    onLogoutClick = onLogout,
                    onNightModeToggle = onToggleNightMode
                )
            }
        }

        BottomNavBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AksaTheme {
        ProfileScreen(
            profileImageRes = R.drawable.profile_picture
        )
    }
}