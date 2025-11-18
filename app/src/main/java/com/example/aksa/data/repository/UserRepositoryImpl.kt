package com.example.aksa.data.repository

import android.content.Context
import android.net.Uri
import com.example.aksa.cache.UserData
import com.example.aksa.data.datastore.UserPreferencesManager
import com.example.aksa.data.remote.firebase.FirebaseProvider
import com.example.aksa.domain.model.User
import com.example.aksa.domain.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import com.example.aksa.R
import com.example.aksa.data.remote.cloudinary.CloudinaryService
import kotlinx.coroutines.flow.first

class UserRepositoryImpl(
    private val userPreferencesManager: UserPreferencesManager,
    private val context: Context
) : UserRepository {

    // firebase auth and firestore
    private val firestore = FirebaseProvider.firestore
    private val auth = FirebaseProvider.auth

    // cloudinary
    private val cloudinaryService = CloudinaryService(context)


    // function to get user from remote
    override suspend fun getUserFromRemote(uid: String): User? {
        val doc = firestore
            .collection("users")
            .document(uid)
            .get()
            .await()

        return doc.toObject(User::class.java)
    }

    // function to check if user is logged in
    override suspend fun isUserLoggedIn(): Boolean {
        val uid = userPreferencesManager.userUid.first()
        return !uid.isNullOrEmpty()
    }

    // function to save user to cache
    override suspend fun saveUserToCache(uid: String, name: String, email: String, photoUrl: String) {
        userPreferencesManager.saveUser(uid, name, email, photoUrl)
        UserData.set(uid, name, email, photoUrl)
    }

    // function to get user uid flow
    override fun getUserUidFlow(): Flow<String?> {
        return userPreferencesManager.userUid
    }

    // function to logout
    override suspend fun logout() {
        userPreferencesManager.clear()
        UserData.clear()
        FirebaseProvider.auth.signOut()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        googleSignInClient.signOut()
    }

    // function untuk edit profile
    override suspend fun editProfile(
        uid: String,
        name: String,
        email: String,
        imageUri: Uri?
    ): Boolean {
        try {
            val photoUrl = if (imageUri != null) {
                cloudinaryService.uploadImage(imageUri)
            } else {
                ""
            }

            // update ke firebase
            val userUpdates = hashMapOf<String, Any>(
                "uid" to uid,
                "name" to name,
                "email" to email,
                "photoUrl" to photoUrl
            )
            firestore.collection("users")
                .document(uid)
                .update(userUpdates)
                .await()

            // update ke datastore
            userPreferencesManager.saveUser(uid, name, email, photoUrl)

            // update ke cache
            UserData.set(uid, name, email, photoUrl)

            return true
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    // function to delete account
    override suspend fun deleteAccount(uid: String) {
        try {
            auth.currentUser?.delete()?.await()

            firestore.collection("users")
                .document(uid)
                .delete()
                .await()

            userPreferencesManager.clear()
            UserData.clear()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
