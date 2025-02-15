package com.example.oyunmerkezi.di


import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.oyunmerkezi.ui.auth.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

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
    @Singleton
    fun provideUserRepository(firebaseAuth: FirebaseAuth): UserRepository = UserRepository(firebaseAuth)

    @Provides
    @Singleton
    fun provideDataStore(context: Application): DataStore<Preferences> {
        return context.dataStore
    }
}