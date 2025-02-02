package com.example.oyunmerkezi.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    // Sign up a user
    fun signUp(email: String, password: String, onResult: (Result<FirebaseUser?>) -> Unit) {
        viewModelScope.launch {
            val result = userRepository.signUp(email, password)
            onResult(result) // Pass the result back to the caller
        }
    }

    // Sign in a user
    fun signIn(email: String, password: String, onResult: (Result<FirebaseUser?>) -> Unit) {
        viewModelScope.launch {
            val result = userRepository.signIn(email, password)
            onResult(result) // Pass the result back to the caller
        }
    }

}