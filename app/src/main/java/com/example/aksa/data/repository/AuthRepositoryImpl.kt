package com.example.aksa.data.repository

import com.example.aksa.data.remote.firebase.FirebaseProvider
import com.example.aksa.domain.repository.AuthRepository
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl : AuthRepository {

    // firebase auth and firestore
    private val auth = FirebaseProvider.auth
    private val firestore = FirebaseProvider.firestore

    // function to login
    override suspend fun login(email: String, password: String): String {
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            return result.user?.uid ?: throw Exception("UID tidak ditemukan")
        } catch (e: Exception) {
            throw Exception(getLocalizedErrorMessage(e.message))
        }
    }


    // function to register
    override suspend fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("UID tidak ditemukan")

            val userData = hashMapOf(
                "uid" to uid,
                "name" to name,
                "email" to email
            )

            firestore.collection("users")
                .document(uid)
                .set(userData)
                .await()

        } catch (e: Exception) {
            throw Exception(getLocalizedErrorMessage(e.message))
        }
    }


    // function to sign in with google
    override suspend fun signInWithGoogle(idToken: String): String {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val uid = result.user?.uid ?: throw Exception("UID tidak ditemukan")

            val userRef = firestore.collection("users").document(uid)
            val userSnapshot = userRef.get().await()

            if (!userSnapshot.exists()) {
                val userData = hashMapOf(
                    "uid" to uid,
                    "name" to (result.user?.displayName ?: ""),
                    "email" to (result.user?.email ?: "")
                )
                userRef.set(userData).await()
            }

            return uid
        } catch (e: Exception) {
            throw Exception(getLocalizedErrorMessage(e.message))
        }
    }


    // function to forgot password
    override suspend fun forgotPassword(email: String) {
        try {
            auth.sendPasswordResetEmail(email).await()
        } catch (e: Exception) {
            throw Exception(getLocalizedErrorMessage(e.message))
        }
    }


    // function to error handling
    private fun getLocalizedErrorMessage(error: String?): String {
        return when {
            error?.contains("badly formatted") == true -> "Format email salah"
            error?.contains("password is invalid") == true -> "Email atau password salah"
            error?.contains("network error") == true -> "Terjadi kesalahan jaringan"
            error?.contains("already in use") == true -> "Email sudah terdaftar"
            else -> error ?: "Terjadi kesalahan. Coba lagi."
        }
    }
}
