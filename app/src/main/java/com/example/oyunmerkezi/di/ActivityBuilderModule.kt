package com.example.oyunmerkezi.di

import com.example.oyunmerkezi.AuthActivity
import com.example.oyunmerkezi.di.auth.AuthViewModelsModule
import com.example.oyunmerkezi.ui.auth.AuthViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules =[ AuthViewModelsModule::class])
    abstract fun contributeAuthActivity(): AuthActivity
}