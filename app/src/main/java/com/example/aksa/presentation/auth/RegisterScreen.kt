package com.example.aksa.presentation.auth

import android.app.Activity
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.presentation.auth.comps.AuthButton
import com.example.aksa.presentation.auth.comps.AuthSocialButton
import com.example.aksa.presentation.common.InputField
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
fun RegisterScreen(
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    authViewModel: AuthViewModel
) {

    val context = LocalContext.current
    val registerState by authViewModel.registerState.collectAsState()
    val regName by authViewModel.regName.collectAsState()
    val regEmail by authViewModel.regEmail.collectAsState()
    val regPassword by authViewModel.regPassword.collectAsState()
    val regPasswordConfirmation by authViewModel.regPasswordConfirmation.collectAsState()

    LaunchedEffect(Unit) {
        authViewModel.resetRegisterState()
        authViewModel.resetRegisterForm()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.result
            val idToken = account.idToken
            if (idToken != null) {
                authViewModel.signInWithGoogleForRegister(idToken)
            }
        }
    }

    when (registerState) {
        is AuthState.Success -> {
            LaunchedEffect(registerState) {
                onNavigateToLogin()
                authViewModel.resetRegisterState()
                authViewModel.resetRegisterForm()
            }
        }

        is AuthState.Error -> {
            val message = (registerState as AuthState.Error).message
            LaunchedEffect(message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                authViewModel.resetRegisterState()
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

                    InputField(
                        value = regName,
                        onValueChange = { authViewModel.onRegNameChange(it) },
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

                    InputField(
                        value = regEmail,
                        onValueChange = { authViewModel.onRegEmailChange(it) },
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

                    InputField(
                        value = regPassword,
                        onValueChange = { authViewModel.onRegPasswordChange(it) },
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

                    InputField(
                        value = regPasswordConfirmation,
                        onValueChange = { authViewModel.onRegPasswordConfirmationChange(it) },
                        placeholder = "Masukkan Kata Sandi Anda",
                        leadingIcon = R.drawable.ic_password,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // register button
                    AuthButton(
                        text = "Daftar",
                        onClick = { authViewModel.register() },
                        isLoading = registerState is AuthState.Loading,
                        enabled = regName.isNotBlank() && regEmail.isNotBlank() && regPassword.isNotBlank() && regPasswordConfirmation.isNotBlank()
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
