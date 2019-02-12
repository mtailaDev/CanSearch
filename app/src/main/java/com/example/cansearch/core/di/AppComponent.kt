package com.example.cansearch.core.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    val retrofit: Retrofit

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun networkModule(module: NetworkModule): Builder
        fun appModule(module: AppModule): Builder
    }
}