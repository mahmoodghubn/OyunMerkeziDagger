package com.example.oyunmerkezilast.di

import android.app.Application
import com.example.oyunmerkezi.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component( modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    AppModule::class,
])
interface AppComponent: AndroidInjector<BaseApplication>//be extending we inject BaseApplication into this component

//BaseApplication is going to be a client for AppComponent
//AppComponent class is a service
{
    @Component.Builder//overriding the component builder method
    interface Builder{
        @BindsInstance//is used when wanna bind a particular instance of an object at time of construction to the component
        fun application(application: Application):Builder
        fun build():AppComponent
    }
}