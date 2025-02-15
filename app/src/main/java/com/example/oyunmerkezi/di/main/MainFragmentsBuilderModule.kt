package com.example.oyunmerkezi.di.main

import com.example.oyunmerkezi.ui.main.GamesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBuilderModule {
    @ContributesAndroidInjector()
    abstract fun contributeGamesFragment(): GamesFragment
}