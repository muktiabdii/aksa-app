package com.example.aksa.presentation.akvault.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc10
import com.example.aksa.ui.theme.Sc100
import com.example.aksa.ui.theme.Sc80

@Composable
fun MuseumSection(
    title: String,
    items: List<ArtifactItem>,
    onArtifactClick: (ArtifactItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Sc100) // background untuk seluruh section
    ) {
        // Header Section
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                color = Sc10
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 16.dp, end = 16.dp)
        )

        // Isi Artifact List
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            items.forEachIndexed { index, artifact ->
                ArtifactItemCard(
                    artifact = artifact,
                    onClick = { onArtifactClick(artifact) },
                    backgroundColor = NonWhite,
                    borderColor = NonWhite
                )

                if (index != items.lastIndex)
                    Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
