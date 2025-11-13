package com.example.aksa.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.auth.comps.AuthButton
import com.example.aksa.presentation.auth.comps.AuthInputField
import com.example.aksa.presentation.auth.comps.AuthSocialButton
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.NonWhite
import com.example.aksa.ui.theme.Sc100
import com.example.aksa.ui.theme.Sc20
import com.example.aksa.ui.theme.Sc80
import com.example.aksa.ui.theme.Sc90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onBackClick: () -> Unit = {},
    onRegisterClick: (String, String, String, String) -> Unit = { _, _, _, _ -> },
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Sc90)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // top bar
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
                    containerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // title section
            Column(
                modifier = Modifier.padding(horizontal = 21.dp)
            ) {

                // title
                Text(
                    text = "Buat Akun",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                        color = NonWhite
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                // sub title
                Text(
                    text = "Silahkan mengisi data untuk membuat akun",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Sc20,
                        lineHeight = 20.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // main section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
                colors = CardDefaults.cardColors(
                    containerColor = NonWhite
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(21.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // name section
                    Text(
                        text = "Name",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                            color = NonBlack
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    AuthInputField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = "Masukkan Nama Anda",
                        leadingIcon = R.drawable.ic_person
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // email section
                    Text(
                        text = "Email",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                            color = NonBlack
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    AuthInputField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Masukkan Email Anda",
                        leadingIcon = R.drawable.ic_email
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // password section
                    Text(
                        text = "Kata Sandi",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                            color = NonBlack
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    AuthInputField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Masukkan Kata Sandi Anda",
                        leadingIcon = R.drawable.ic_password,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // password confirmation section
                    Text(
                        text = "Konfirmasi Kata Sandi",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                            color = NonBlack
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    AuthInputField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = "Masukkan Kata Sandi Anda",
                        leadingIcon = R.drawable.ic_password,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // register button
                    AuthButton(
                        text = "Daftar",
                        onClick = {
                            isLoading = true
                            onRegisterClick(name, email, password, confirmPassword)
                        },
                        isLoading = isLoading,
                        enabled = name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // text with divider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Divider(
                            modifier = Modifier
                                .weight(1f)
                                .height(1.dp),
                            color = Sc100
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "atau daftar dengan",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_medium)),
                                color = Sc80
                            )
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Divider(
                            modifier = Modifier
                                .weight(1f)
                                .height(1.dp),
                            color = Sc100
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // social button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AuthSocialButton(
                            text = "Google",
                            onClick = onGoogleClick,
                            leadingIcon = R.drawable.ic_google,
                            modifier = Modifier.weight(1f)
                        )

                        AuthSocialButton(
                            text = "Facebook",
                            onClick = onFacebookClick,
                            leadingIcon = R.drawable.ic_facebook,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // register button
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val annotatedText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = NonBlack
                    )
                ) {
                    append("Sudah memiliki akun? ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        color = NonBlack
                    )
                ) {
                    append("Masuk")
                }
            }

            TextButton(
                onClick = onLoginClick,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = annotatedText)
            }
        }
    }
}
