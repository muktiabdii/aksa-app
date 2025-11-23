package com.example.aksa.presentation.akview.contribution

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.aksa.presentation.common.AngularGradientShape
import com.example.aksa.presentation.common.InputField
import com.example.aksa.presentation.common.TopBar
import com.example.aksa.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContributionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSubmitClick: () -> Unit = {}
) {
    // State Form
    var selectedMuseum by remember { mutableStateOf("") }
    var otherMuseumName by remember { mutableStateOf("") }
    var locationArea by remember { mutableStateOf("") }
    var selectedFileName by remember { mutableStateOf("") }

    // Dummy State untuk Dropdown
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val museumOptions = listOf("Museum Nasional", "Museum Batik", "Museum Ulen Sentanu", "Lainnya")

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // background
        AngularGradientShape(
            offsetX = 250f,
            offsetY = -100f,
            width = 400f,
            height = 400f
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
                title = "AkView",
                onBackClick = onBackClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Sc90),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Kontribusi Sekarang",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Nama museum
                LabelText(text = "Nama Museum")
                Spacer(modifier = Modifier.height(8.dp))

                Box {
                    OutlinedTextField(
                        value = if (selectedMuseum.isEmpty()) "Pilih Museum" else selectedMuseum,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable { isDropdownExpanded = true },
                        shape = RoundedCornerShape(30.dp),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_down),
                                contentDescription = "Dropdown",
                                tint = NonBlack,
                                modifier = Modifier.clickable { isDropdownExpanded = true }
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = if (selectedMuseum.isEmpty()) Nr40 else NonBlack
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Sc70,
                            unfocusedIndicatorColor = Sc70,
                            disabledContainerColor = Color.White,
                            disabledIndicatorColor = Sc70,
                            disabledTextColor = NonBlack,
                            disabledPlaceholderColor = Nr40,
                            disabledTrailingIconColor = NonBlack
                        ),
                        enabled = false
                    )

                    // Overlay Clickable
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { isDropdownExpanded = true }
                    )

                    // Dropdown Menu
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        museumOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option, fontFamily = FontFamily(Font(R.font.nunito_regular))) },
                                onClick = {
                                    selectedMuseum = option
                                    isDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // Conditional Input
                if (selectedMuseum == "Lainnya") {
                    Spacer(modifier = Modifier.height(16.dp))
                    LabelText(text = "*Isikan Nama Museum jika memilih Lainnya", color = Nr60)
                    Spacer(modifier = Modifier.height(8.dp))
                    InputField(
                        value = otherMuseumName,
                        onValueChange = { otherMuseumName = it },
                        placeholder = "Nama Museum",
                        focusedIndicatorColor = Sc70,
                        unfocusedIndicatorColor = Nr30
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lokasi Area
                LabelText(text = "Lokasi Area")
                Spacer(modifier = Modifier.height(8.dp))
                InputField(
                    value = locationArea,
                    onValueChange = { locationArea = it },
                    placeholder = "Pilih Lokasi",
                    leadingIcon = R.drawable.ic_location,
                    focusedIndicatorColor = Sc70,
                    unfocusedIndicatorColor = Nr30
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Upload Foto
                LabelText(text = "Upload Foto")
                Spacer(modifier = Modifier.height(8.dp))

                Box {
                    OutlinedTextField(
                        value = if (selectedFileName.isEmpty()) "Pilih Foto" else selectedFileName,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(30.dp),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_upload),
                                contentDescription = "Upload",
                                tint = NonBlack
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = if (selectedFileName.isEmpty()) Nr40 else NonBlack
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Sc70,
                            unfocusedIndicatorColor = Sc70,
                            disabledContainerColor = Color.White,
                            disabledIndicatorColor = Sc70,
                            disabledTextColor = NonBlack,
                            disabledPlaceholderColor = Nr40,
                            disabledTrailingIconColor = NonBlack
                        ),
                        enabled = false
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable {
                                selectedFileName = "foto_museum.jpg"
                            }
                    )
                }

                Spacer(modifier = Modifier.weight(1f).heightIn(min = 100.dp))

                // Tombol Submit
                Button(
                    onClick = onSubmitClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedMuseum.isNotEmpty() && (selectedMuseum != "Lainnya" || otherMuseumName.isNotEmpty()) && locationArea.isNotEmpty()) Sc80 else Color.Gray
                    ),
                    enabled = selectedMuseum.isNotEmpty()
                ) {
                    Text(
                        text = "Tambahkan",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                            color = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun LabelText(text: String, color: Color = NonBlack) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            color = color
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContributionScreenPreview() {
    ContributionScreen()
}