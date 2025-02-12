package com.example.oyunmerkezi.di.auth

import androidx.lifecycle.ViewModel
import com.example.oyunmerkezi.di.ViewModelKey
import com.example.oyunmerkezi.ui.main.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindAuthViewModel(gameViewModel: GameViewModel): ViewModel
}