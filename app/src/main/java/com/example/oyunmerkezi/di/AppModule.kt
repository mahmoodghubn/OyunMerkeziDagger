package com.example.oyunmerkezilast.di

import dagger.Module
import dagger.Provides

@Module
class AppModule {
//where to put application level dependencies

    @Provides
     fun getString():String{
         return "hi there"
     }

}