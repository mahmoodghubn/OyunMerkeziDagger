package com.example.oyunmerkezi.di.auth

import androidx.lifecycle.ViewModel
import com.example.oyunmerkezi.di.ViewModelKey
import com.example.oyunmerkezi.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
//responsible for doing dependency injection for ViewModelFactoryClass
@Module
abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel
}