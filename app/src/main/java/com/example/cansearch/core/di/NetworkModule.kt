package com.example.cansearch.core.di

import com.example.cansearch.core.data.ConnectivityInterceptor
import com.example.cansearch.core.domain.CanSearchApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(): ConnectivityInterceptor = ConnectivityInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(connectivityInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideCallAdapter(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://clinicaltrialsapi.cancer.gov/v1/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun canSearchApiService(retrofit: Retrofit): CanSearchApiService {
        return retrofit.create(CanSearchApiService::class.java)
    }
}