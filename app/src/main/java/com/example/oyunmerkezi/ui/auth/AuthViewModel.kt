package com.example.oyunmerkezi.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class AuthViewModel : ViewModel {
    private val TAG = "AuthViewModel"
    @Inject
    constructor() {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
    }
}