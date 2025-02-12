package com.example.oyunmerkezi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.oyunmerkezi.ui.auth.AuthViewModel
import com.example.oyunmerkezi.ui.auth.UserRepository
import com.example.oyunmerkezi.ui.main.GameViewModel
import com.example.oyunmerkezi.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var authViewModel:AuthViewModel
    @Inject lateinit var userRepository: UserRepository

    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authViewModel = ViewModelProviders.of(this,providerFactory)[AuthViewModel::class.java]

        findViewById<Button>(R.id.submit).setOnClickListener {
            val email = findViewById<EditText>(R.id.email_edit_text).editableText.toString()
            val password = findViewById<EditText>(R.id.password_edit_text).editableText.toString()

            authViewModel.signUp(email, password) { result ->
                result.onSuccess { user ->
                    android.widget.Toast.makeText(this, "Sign-up successful: ${user?.email}", android.widget.Toast.LENGTH_LONG).show()
                }.onFailure { exception ->
                    android.widget.Toast.makeText(this, "Sign-up failed: ${exception.message}", android.widget.Toast.LENGTH_LONG).show()
                }
            }

        }

        if (userRepository.isLoggedIn()) {
            println("Logged in user: ${userRepository.getCurrentUser()?.email}")
        } else {
            println("No user is logged in.")
        }




    }

}

