package com.example.cansearch.search.di

import com.example.cansearch.search.data.SearchApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchModule {

    @Provides
    @Search
    fun searchApiService(retrofit: Retrofit) : SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }
}