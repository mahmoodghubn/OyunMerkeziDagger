package com.example.oyunmerkezi.di.main

import android.app.Application
import com.example.oyunmerkezi.database.GameDao
import com.example.oyunmerkezi.database.GameDatabase
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun provideGameDao(application: Application):GameDao{
        return GameDatabase.getInstance(application).gameDao

    }
}