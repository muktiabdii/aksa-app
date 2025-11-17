package com.example.aksa.presentation.auth

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onBackClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    authViewModel: AuthViewModel
) {

    val context = LocalContext.current
    val loginState by authViewModel.loginState.collectAsState()
    val loginEmail by authViewModel.loginEmail.collectAsState()
    val loginPassword by authViewModel.loginPassword.collectAsState()

    LaunchedEffect(Unit) {
        authViewModel.resetLoginState()
        authViewModel.resetLoginForm()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.result
            val idToken = account.idToken
            if (idToken != null) {
                authViewModel.signInWithGoogleForLogin(idToken)
            }
        }
    }

    when (loginState) {
        is AuthState.Success -> {
            LaunchedEffect(loginState) {
                onNavigateToHome()
                authViewModel.resetLoginState()
                authViewModel.resetLoginForm()
            }
        }

        is AuthState.Error -> {
            val message = (loginState as AuthState.Error).message
            LaunchedEffect(message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                authViewModel.resetLoginState()
            }
        }

        else -> Unit
    }

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
                    text = "Selamat Datang",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                        color = NonWhite
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                // sub title
                Text(
                    text = "Silahkan mengisi data dengan akun yang sudah didaftarkan sebelumnya",
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
                        value = loginEmail,
                        onValueChange = { authViewModel.onLoginEmailChange(it) },
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
                        value = loginPassword,
                        onValueChange = { authViewModel.onLoginPasswordChange(it) },
                        placeholder = "Masukkan Kata Sandi Anda",
                        leadingIcon = R.drawable.ic_password,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // forgot password
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = onForgotPasswordClick) {
                            Text(
                                text = "Lupa kata sandi ?",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                                    color = Color(0xFFFF0000)
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // login button
                    AuthButton(
                        text = "Masuk",
                        onClick = { authViewModel.login() },
                        isLoading = loginState is AuthState.Loading,
                        enabled = loginEmail.isNotBlank() && loginPassword.isNotBlank()
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
                            text = "atau masuk dengan",
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
                            onClick = {
                                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                    .requestIdToken("1093120466862-o0bihdgorromv5bnp0hn46cgdu8ss0tk.apps.googleusercontent.com")
                                    .requestEmail()
                                    .build()

                                val googleSignInClient = GoogleSignIn.getClient(context, gso)

                                googleSignInClient.signOut().addOnCompleteListener {
                                    launcher.launch(googleSignInClient.signInIntent)
                                }
                            },
                            leadingIcon = R.drawable.ic_google,
                            modifier = Modifier.weight(1f)
                        )


                        AuthSocialButton(
                            text = "Facebook",
                            onClick = {  },
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
                    append("Belum memiliki akun? ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        color = NonBlack
                    )
                ) {
                    append("Daftar")
                }
            }

            TextButton(
                onClick = onRegisterClick,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = annotatedText)
            }
        }
    }
}
