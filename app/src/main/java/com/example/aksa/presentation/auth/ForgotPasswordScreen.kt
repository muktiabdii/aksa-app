package com.example.aksa.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.auth.comps.AuthButton
import com.example.aksa.presentation.common.InputField
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.Sc90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onBackClick: () -> Unit = {},
    onSubmitClick: (String) -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Bar
            TopAppBar(
                title = { },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable { onBackClick() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )

            // content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // image
                Image(
                    painter = painterResource(id = R.drawable.img_header_forgot_password),
                    contentDescription = "Forgot Password Illustration",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                // title
                Text(
                    text = "Lupa Kata Sandi",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        color = NonBlack,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // sub title
                Text(
                    text = "Silahkan masukkan alamat anda untuk meminta pengaturan ulang kata sandi",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Sc90,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // email section
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Email",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                            color = NonBlack
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                InputField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Masukkan Email Anda",
                    leadingIcon = R.drawable.ic_email
                )

                Spacer(modifier = Modifier.height(24.dp))

                // submit button
                AuthButton(
                    text = "Kirim",
                    onClick = {
                        isLoading = true
                        onSubmitClick(email)
                    },
                    isLoading = isLoading,
                    enabled = email.isNotBlank()
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}