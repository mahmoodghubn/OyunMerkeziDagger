package com.example.oyunmerkezi.di

import com.example.oyunmerkezi.AuthActivity
import com.example.oyunmerkezi.di.auth.AuthViewModelsModule
import com.example.oyunmerkezi.ui.auth.AuthViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules =[ AuthViewModelsModule::class])//this will create sub component behind the scene
    // sub component for organization and scooping
    abstract fun contributeAuthActivity(): AuthActivity
}