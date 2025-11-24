package com.example.aksa.presentation.akview.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonBlack

@Composable
fun ViewerControls(
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
    onFullscreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        ControlIcon(iconRes = R.drawable.ic_add, onClick = onZoomIn)
        ControlIcon(iconRes = R.drawable.ic_min, onClick = onZoomOut)
        ControlIcon(iconRes = R.drawable.ic_fullscreen, onClick = onFullscreen)
    }
}

@Composable
private fun ControlIcon(
    iconRes: Int,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White.copy(alpha = 0.8f))
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = NonBlack,
            modifier = Modifier.size(18.dp)
        )
    }
}