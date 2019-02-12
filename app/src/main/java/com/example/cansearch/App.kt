package com.example.cansearch

import android.app.Application
import com.example.cansearch.core.di.AppComponent
import com.example.cansearch.core.di.AppModule
import com.example.cansearch.core.di.DaggerAppComponent
import com.example.cansearch.core.di.NetworkModule

class App : Application() {

    val appComponent: AppComponent by lazy {
            DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @JvmStatic
        fun daggerAppComponent() = instance.appComponent

        lateinit var instance: App
            private set
    }
}