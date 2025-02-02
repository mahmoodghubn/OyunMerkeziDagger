package com.example.oyunmerkezi.ui.auth
import com.example.oyunmerkezi.util.User
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class UserRepository @Inject constructor(private val firebaseAuth: FirebaseAuth

):AccountService {
    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun isLoggedIn(): Boolean = firebaseAuth.currentUser != null

//    override val currentUser: Flow<User?>
//        get() = callbackFlow {
//            val listener =
//                FirebaseAuth.AuthStateListener { auth ->
//                    this.trySend(auth.currentUser.toNotesUser())
//                }
//            Firebase.auth.addAuthStateListener(listener)
//            awaitClose { Firebase.auth.removeAuthStateListener(listener) }
//        }

    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    override fun hasUser(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun getUserProfile(): User? {
//        return firebaseAuth.currentUser.toNotesUser()
        return null
    }

    override suspend fun createAnonymousAccount() {
        firebaseAuth.signInAnonymously().await()
    }

    override suspend fun updateDisplayName(newDisplayName: String) {
        TODO("Not yet implemented")
    }

//    override suspend fun updateDisplayName(newDisplayName: String) {
//        val profileUpdates = userProfileChangeRequest {
//            displayName = newDisplayName
//        }
//
//        Firebase.auth.currentUser!!.updateProfile(profileUpdates).await()
//    }

    override suspend fun linkAccount(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)
        firebaseAuth.currentUser!!.linkWithCredential(credential).await()
    }

//    override suspend fun signIn(email: String, password: String) {
//        firebaseAuth.signInWithEmailAndPassword(email, password).await()
//    }
//    override suspend fun signUp(email: String, password: String) {
//        firebaseAuth.signInWithEmailAndPassword(email, password).await()
//    }

    override suspend fun signOut() {
        firebaseAuth.signOut()

        // Sign the user back in anonymously.
        createAnonymousAccount()
    }

    override suspend fun deleteAccount() {
        firebaseAuth.currentUser!!.delete().await()
    }
    override suspend fun signUp(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val authResult: AuthResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(authResult.user) // Return the signed-up user
        } catch (e: Exception) {
            Result.failure(e) // Handle any exceptions
        }
    }

    // Sign in an existing user
    override suspend fun signIn(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val authResult: AuthResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(authResult.user) // Return the signed-in user
        } catch (e: Exception) {
            Result.failure(e) // Handle any exceptions
        }
    }}