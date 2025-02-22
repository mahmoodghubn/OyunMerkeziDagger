package com.example.oyunmerkezi.di

import com.example.oyunmerkezi.AuthActivity
import com.example.oyunmerkezi.MainActivity
import com.example.oyunmerkezi.di.auth.AuthViewModelsModule
import com.example.oyunmerkezi.di.auth.MainViewModelsModule
import com.example.oyunmerkezi.di.main.MainActivityModule
import com.example.oyunmerkezi.di.main.MainFragmentsBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules =[ AuthViewModelsModule::class])//this will create sub component behind the scene
    // sub component for organization and scooping
    abstract fun contributeAuthActivity(): AuthActivity
    @ContributesAndroidInjector(modules =[MainViewModelsModule::class,MainFragmentsBuilderModule::class,MainActivityModule::class])//this will create sub component behind the scene
    abstract fun contributeMainActivity(): MainActivity
}