package com.example.aksa.presentation.home.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.example.aksa.ui.theme.NonWhite

@Composable
fun MuseumCardHome(
    museum: Museum,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(180.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Museum Image
            Image(
                painter = painterResource(id = museum.image),
                contentDescription = museum.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color(0xFF502F21).copy(alpha = 0f),
                                0.75f to Color(0xFF502F21).copy(alpha = 0.9f),
                                1.0f to Color(0xFF502F21).copy(alpha = 1f)
                            )
                        )
                    )
            )

            // Museum Name
            Text(
                text = museum.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = NonWhite
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(14.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun MuseumCardPreview() {
    val sampleMuseum = Museum(
        name = "Museum Nasional",
        image = R.drawable.img_museum_nasional
    )

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        MuseumCardHome(
            museum = sampleMuseum,
            onClick = {}
        )
    }
}
