package com.example.aksa.presentation.profile.comps

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Nr20
import com.example.aksa.ui.theme.Nr60
import com.example.aksa.ui.theme.Nr90
import com.example.aksa.ui.theme.Sc10
import com.example.aksa.ui.theme.Sc100
import com.example.aksa.ui.theme.Sc30
import com.example.aksa.ui.theme.nunitoFamily

data class SettingsMenuItem(
    val icon: Int,
    val title: String,
    val hasToggle: Boolean = false,
    val onClick: () -> Unit = {}
)

@Composable
fun SettingsMenu(
    modifier: Modifier = Modifier,
    onNotificationToggle: (Boolean) -> Unit = {},
    onFaqClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onSecurityClick: () -> Unit = {},
    onDeleteAccountClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onNightModeToggle: (Boolean) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = NonWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Pengaturan",
                fontFamily = nunitoFamily,
                fontWeight = FontWeight.SemiBold,
                color = Nr90,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            SettingsToggleItem(
                icon = R.drawable.ic_notification,
                title = "Notifikasi",
                onToggle = onNotificationToggle
            )

            SettingsToggleItem(
                icon = R.drawable.ic_nightmode,
                title = "Mode malam",
                onToggle = onNightModeToggle
            )

            SettingsItem(
                icon = R.drawable.ic_help,
                title = "FAQ",
                onClick = onFaqClick
            )

            SettingsItem(
                icon = R.drawable.ic_about,
                title = "Tentang Aksa",
                onClick = onAboutClick
            )

            SettingsItem(
                icon = R.drawable.ic_language,
                title = "Bahasa",
                onClick = onLanguageClick
            )

            SettingsItem(
                icon = R.drawable.ic_security,
                title = "Keamanan",
                onClick = onSecurityClick
            )

            SettingsItem(
                icon = R.drawable.ic_delete_acc,
                title = "Hapus akun",
                onClick = onDeleteAccountClick
            )

            SettingsItem(
                icon = R.drawable.ic_logout,
                title = "Keluar",
                onClick = onLogoutClick,
                showDivider = false
            )
        }
    }
}

@Composable
private fun SettingsItem(
    icon: Int,
    title: String,
    onClick: () -> Unit,
    showDivider: Boolean = true
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = Nr90
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = Nr90,
                modifier = Modifier.weight(1f)
            )

            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = "Navigate",
                modifier = Modifier.size(20.dp),
                tint = Nr60
            )
        }

        if (showDivider) {
            HorizontalDivider(
                color = Nr20,
                thickness = 0.5.dp
            )
        }
    }
}

@Composable
private fun SettingsToggleItem(
    icon: Int,
    title: String,
    onToggle: (Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = Nr90
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = Nr90,
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    onToggle(it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = NonWhite,
                    checkedTrackColor = Sc100,
                    uncheckedThumbColor = Sc100,
                    uncheckedTrackColor = Sc10,
                    uncheckedBorderColor = Sc100
                )
            )
        }

        HorizontalDivider(
            color = Nr20,
            thickness = 0.5.dp
        )
    }
}