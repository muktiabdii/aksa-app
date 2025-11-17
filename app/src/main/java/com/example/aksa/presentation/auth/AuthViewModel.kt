package com.example.aksa.presentation.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksa.domain.usecase.AuthUseCase
import com.example.aksa.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// General state
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}

// Auth flow type
enum class AuthFlowType {
    LOGIN, REGISTER
}

/**
 * ViewModel yang menangani seluruh proses autentikasi aplikasi:
 * - Login
 * - Register
 * - Forgot Password
 * - Google Sign-In
 *
 * ViewModel ini memisahkan:
 * 1. Local Form State     → form input per screen
 * 2. Request State        → status loading/success/error tiap operasi
 *
 * Menggunakan StateFlow agar perubahan state dapat diobservasi oleh UI Compose.
 *
 * @property authUseCase Use case untuk proses autentikasi Firebase.
 * @property userUseCase Use case untuk pemuatan & penyimpanan data user.
 */
class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    // ================================
    // LOCAL FORM STATE
    // ================================

    /** Email untuk halaman login. */
    val loginEmail = MutableStateFlow("")

    /** Password untuk halaman login. */
    val loginPassword = MutableStateFlow("")

    /** Mengubah email login. */
    fun onLoginEmailChange(email: String) { loginEmail.value = email }

    /** Mengubah password login. */
    fun onLoginPasswordChange(password: String) { loginPassword.value = password }


    // REGISTER SCREEN

    /** Nama user untuk registrasi. */
    val regName = MutableStateFlow("")

    /** Email user untuk registrasi. */
    val regEmail = MutableStateFlow("")

    /** Password user untuk registrasi. */
    val regPassword = MutableStateFlow("")

    /** Konfirmasi password user. */
    val regPasswordConfirmation = MutableStateFlow("")

    fun onRegNameChange(name: String) { regName.value = name }
    fun onRegEmailChange(email: String) { regEmail.value = email }
    fun onRegPasswordChange(password: String) { regPassword.value = password }
    fun onRegPasswordConfirmationChange(passwordConfirmation: String) {
        regPasswordConfirmation.value = passwordConfirmation
    }


    // FORGOT PASSWORD SCREEN

    /** Email untuk pengiriman reset password. */
    val forgotEmail = MutableStateFlow("")

    fun onForgotEmailChange(email: String) { forgotEmail.value = email }


    // ================================
    // REQUEST STATE
    // ================================

    /** State proses login. */
    private val _loginState = MutableStateFlow<AuthState>(AuthState.Idle)
    val loginState: StateFlow<AuthState> = _loginState

    /** State proses registrasi. */
    private val _registerState = MutableStateFlow<AuthState>(AuthState.Idle)
    val registerState: StateFlow<AuthState> = _registerState

    /** State proses forgot password. */
    private val _forgotPasswordState = MutableStateFlow<AuthState>(AuthState.Idle)
    val forgotPasswordState: StateFlow<AuthState> = _forgotPasswordState

    /** State proses Google Sign-In. */
    private val _googleLoginState = MutableStateFlow<AuthState>(AuthState.Idle)
    val googleLoginState: StateFlow<AuthState> = _googleLoginState


    // ================================
    // RESET REQUEST STATE
    // ================================

    /** Reset state login kembali ke Idle. */
    fun resetLoginState() { _loginState.value = AuthState.Idle }

    /** Reset state registrasi kembali ke Idle. */
    fun resetRegisterState() { _registerState.value = AuthState.Idle }

    /** Reset state forgot password ke Idle. */
    fun resetForgotPasswordState() { _forgotPasswordState.value = AuthState.Idle }

    /** Reset state Google Sign-In ke Idle. */
    fun resetGoogleLoginState() { _googleLoginState.value = AuthState.Idle }


    // ================================
    // RESET FORM STATE
    // ================================

    /** Mengosongkan seluruh input form login. */
    fun resetLoginForm() {
        loginEmail.value = ""
        loginPassword.value = ""
    }

    /** Mengosongkan seluruh input form registrasi. */
    fun resetRegisterForm() {
        regName.value = ""
        regEmail.value = ""
        regPassword.value = ""
        regPasswordConfirmation.value = ""
    }

    /** Mengosongkan form forgot password. */
    fun resetForgotForm() {
        forgotEmail.value = ""
    }


    // ================================
    // LOGIN
    // ================================

    /**
     * Melakukan login menggunakan email dan password.
     * Mengatur state menjadi:
     * - Loading
     * - Success
     * - Error
     */
    fun login() {
        viewModelScope.launch {
            _loginState.value = AuthState.Loading

            val result = authUseCase.login(loginEmail.value, loginPassword.value)

            result.onSuccess { uid ->
                if (uid.isNotEmpty()) loadUser(uid)
                _loginState.value = AuthState.Success
            }.onFailure { e ->
                _loginState.value = AuthState.Error(e.message ?: "Login gagal")
            }
        }
    }

    /**
     * Memuat data user dari remote dan menyimpannya ke cache lokal.
     *
     * @param uid UID Firebase user.
     */
    private suspend fun loadUser(uid: String) {
        val user = userUseCase.getUserFromRemote(uid)
        if (user != null) {
            userUseCase.saveUserToCache(
                user.uid,
                user.name,
                user.email,
                user.photoUrl
            )
        }
    }


    // ================================
    // REGISTER
    // ================================

    /**
     * Melakukan registrasi dengan data yang diisi user.
     * Menyet state sesuai hasil proses.
     */
    fun register() {
        viewModelScope.launch {
            _registerState.value = AuthState.Loading

            val result = authUseCase.register(
                regName.value,
                regEmail.value,
                regPassword.value,
                regPasswordConfirmation.value
            )

            result.onSuccess {
                _registerState.value = AuthState.Success
            }.onFailure { e ->
                _registerState.value = AuthState.Error(e.message ?: "Register gagal")
            }
        }
    }


    // ================================
    // FORGOT PASSWORD
    // ================================

    /**
     * Mengirim email reset password.
     */
    fun forgotPassword() {
        viewModelScope.launch {
            _forgotPasswordState.value = AuthState.Loading

            val result = authUseCase.forgotPassword(forgotEmail.value)

            result.onSuccess {
                _forgotPasswordState.value = AuthState.Success
            }.onFailure { e ->
                _forgotPasswordState.value = AuthState.Error(
                    e.message ?: "Gagal mengirim reset password"
                )
            }
        }
    }


    // ================================
    // GOOGLE SIGN-IN
    // ================================

    /**
     * Handler utama untuk login/register menggunakan Google.
     *
     * @param idToken Token ID dari Google.
     * @param type Type flow: LOGIN atau REGISTER.
     */
    private fun handleGoogleSignIn(idToken: String, type: AuthFlowType) {
        val state = when (type) {
            AuthFlowType.LOGIN -> _loginState
            AuthFlowType.REGISTER -> _registerState
        }

        viewModelScope.launch {
            state.value = AuthState.Loading

            val result = authUseCase.signInWithGoogle(idToken)

            result.onSuccess { uid ->
                if (uid.isNotEmpty()) loadUser(uid)
                state.value = AuthState.Success
            }.onFailure { e ->
                state.value = AuthState.Error(e.message ?: "Google sign-in gagal")
            }
        }
    }

    /** Login menggunakan Google. */
    fun signInWithGoogleForLogin(idToken: String) {
        handleGoogleSignIn(idToken, AuthFlowType.LOGIN)
    }

    /** Register menggunakan Google. */
    fun signInWithGoogleForRegister(idToken: String) {
        handleGoogleSignIn(idToken, AuthFlowType.REGISTER)
    }
}
