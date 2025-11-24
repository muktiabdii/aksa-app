package com.example.aksa.presentation.home.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Nr10
import com.example.aksa.ui.theme.Sc10
import com.example.aksa.ui.theme.Sc100
import com.example.aksa.ui.theme.Sc20
import com.example.aksa.ui.theme.Sc30
import com.example.aksa.ui.theme.Sc90

@Composable
fun MonthlySummaryCard(
    totalPoints: Int = 842,
    questCount: Int = 100,
    visitCount: Int = 10,
    progressPercentage: Int = 42,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Sc90)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left side - Stats
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Title with icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_fire),
                            contentDescription = "Fire Icon",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Ringkasanmu Bulan Ini",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                color = Sc10
                            )
                        )
                    }

                    // Total points
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = totalPoints.toString(),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                                color = Sc30,
                                lineHeight = 48.sp
                            )
                        )
                        Text(
                            text = "poin didapatkan",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_medium)),
                                color = Sc10
                            ),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }

                    // Quest and Visit badges
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatBadge(
                            count = questCount,
                            label = "quest"
                        )
                        StatBadge(
                            count = visitCount,
                            label = "visit"
                        )
                    }
                }

                // Right side - Circular Progress
                Box(
                    modifier = Modifier.size(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        percentage = progressPercentage
                    )
                }
            }
        }
    }
}

@Composable
fun StatBadge(
    count: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = Sc20
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = count.toString(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_medium)),
                    color = Sc100
                )
            )
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_medium)),
                    color = Sc100
                )
            )
        }
    }
}

@Composable
fun CircularProgressIndicator(
    percentage: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(90.dp),
        contentAlignment = Alignment.Center
    ) {
        // Background circle
        androidx.compose.foundation.Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val strokeWidth = 16.dp.toPx()

            // Background arc
            drawArc(
                color = Nr10,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = strokeWidth,
                    cap = androidx.compose.ui.graphics.StrokeCap.Round
                )
            )

            // Progress arc
            drawArc(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEEC8B7),
                        Color(0xFF632610)
                    )
                ),
                startAngle = -90f,
                sweepAngle = (percentage / 100f) * 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = strokeWidth,
                    cap = androidx.compose.ui.graphics.StrokeCap.Round
                )
            )
        }

        // Percentage text
        Text(
            text = "$percentage%",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                color = Sc20
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MonthlySummaryCardPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        MonthlySummaryCard()
    }
}