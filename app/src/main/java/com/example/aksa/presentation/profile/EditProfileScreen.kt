package com.example.aksa.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.common.InputField
import com.example.aksa.presentation.common.TopBar
import com.example.aksa.presentation.common.AngularGradientShape
import com.example.aksa.ui.theme.*

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    var nama by remember { mutableStateOf("angkasa") }
    var username by remember { mutableStateOf("angkasaksa") }
    var email by remember { mutableStateOf("angkasa123@gmail.com") }
    var password by remember { mutableStateOf("********") }
    var confirmPassword by remember { mutableStateOf("********") }

    Box(
        modifier = Modifier
            .fillMaxSize()
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

        // Content
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            // Top Bar
            TopBar(
                title = "Edit Profile",
                onBackClick = onBackClick
            )

            // Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Profile Picture
                Box(
                    modifier = Modifier.size(120.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    // AsyncImage atau Image
                    Image(
                        painter = painterResource(id = R.drawable.profile_picture),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )

                    // Edit Icon
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .clickable { /* Handle edit photo */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pencil),
                            contentDescription = "Edit Photo",
                            modifier = Modifier.size(16.dp),
                            tint = NonBlack
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Nama
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Nama",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = NonBlack
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    InputField(
                        value = nama,
                        onValueChange = { nama = it },
                        placeholder = "Nama",
                        leadingIcon = R.drawable.ic_person,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Sc70,
                        unfocusedIndicatorColor = Nr30
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Username
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Username",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = NonBlack
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    InputField(
                        value = username,
                        onValueChange = { username = it },
                        placeholder = "Username",
                        leadingIcon = R.drawable.ic_person,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Sc70,
                        unfocusedIndicatorColor = Nr30
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Email
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Email",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = NonBlack
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    InputField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Email",
                        leadingIcon = R.drawable.ic_email,
                        keyboardType = KeyboardType.Email,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Sc70,
                        unfocusedIndicatorColor = Nr30
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Kata Sandi
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Kata Sandi",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = NonBlack
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    InputField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Kata Sandi",
                        leadingIcon = R.drawable.ic_lock,
                        isPassword = true,
                        showPasswordToggle = true,
                        visibleIcon = R.drawable.ic_eye_on,
                        hiddenIcon = R.drawable.ic_eye_off,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Sc70,
                        unfocusedIndicatorColor = Nr30
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Konfirmasi Kata Sandi
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Konfirmasi Kata Sandi",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = NonBlack
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    InputField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = "Konfirmasi Kata Sandi",
                        leadingIcon = R.drawable.ic_lock,
                        isPassword = true,
                        showPasswordToggle = true,
                        visibleIcon = R.drawable.ic_eye_on,
                        hiddenIcon = R.drawable.ic_eye_off,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Sc70,
                        unfocusedIndicatorColor = Nr30
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Simpan Button
                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Sc80
                    )
                ) {
                    Text(
                        text = "Simpan",
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen()
}