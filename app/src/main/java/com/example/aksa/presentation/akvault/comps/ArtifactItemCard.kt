package com.example.aksa.presentation.akvault.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.NonWhite


@Composable
fun ArtifactItemCard(
    artifact: ArtifactItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = NonWhite
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Artifact Image
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = artifact.image),
                    contentDescription = artifact.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Artifact Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = artifact.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_medium)),
                        color = NonBlack
                    )
                )
                Text(
                    text = artifact.location,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_medium)),
                        color = NonBlack.copy(alpha = 0.6f)
                    )
                )
            }

            // Arrow Button
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right_akvault),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun sdf() {
    ArtifactItemCard(
        artifact = ArtifactItem(
            name = "Arca Ganesha",
            location = "Lihat detail alamatnya",
            image = R.drawable.img_header_forgot_password,
            museumCategory = "Museum Nasional"
        ),
        onClick = {}
    )
}