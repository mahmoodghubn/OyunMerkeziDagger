package com.example.oyunmerkezi.di

import com.example.oyunmerkezi.ui.auth.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
//where to put application level dependencies

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideCurrentUser(firebaseAuth: FirebaseAuth): FirebaseUser? = firebaseAuth.currentUser


    @Provides
    fun provideUserRepository(firebaseAuth: FirebaseAuth): UserRepository = UserRepository(firebaseAuth)
}