package com.example.oyunmerkezi.ui.auth

import com.example.oyunmerkezi.util.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AccountService {
//    val currentUser: Flow<User?>
    val currentUserId: String
    fun hasUser(): Boolean
    fun getUserProfile(): User?
    suspend fun createAnonymousAccount()
    suspend fun updateDisplayName(newDisplayName: String)
    suspend fun linkAccount(email: String, password: String)
    suspend fun signIn(email: String, password: String):Result<FirebaseUser?>
    suspend fun signUp(email: String, password: String):Result<FirebaseUser?>
    suspend fun signOut()
    suspend fun deleteAccount()
}
