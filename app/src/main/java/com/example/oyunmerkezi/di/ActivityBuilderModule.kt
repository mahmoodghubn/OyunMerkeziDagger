package com.example.oyunmerkezi.di

import com.example.oyunmerkezi.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity
}