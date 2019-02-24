package com.example.cansearch.search.di

import com.example.cansearch.core.data.MemoryReactiveStore
import com.example.cansearch.search.data.SearchApiService
import com.example.cansearch.search.data.SearchRemoteDataSource
import com.example.cansearch.search.data.SearchRepository
import com.example.cansearch.search.domain.FetchSearchUseCase
import com.example.cansearch.search.domain.GetSearchUseCase
import com.example.cansearch.search.domain.SearchScreen
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.ConcurrentHashMap

@Module
class SearchModule {

    @Provides
    @Search
    fun searchApiService(retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

    @Provides
    @Search
    fun marketRepository(
        remoteDataSource: SearchRemoteDataSource,
        reactiveStore: MemoryReactiveStore<String, SearchScreen>
    ): SearchRepository {
        return SearchRepository(remoteDataSource, reactiveStore)
    }

    @Provides
    @Search
    fun searchReactiveStore(): MemoryReactiveStore<String, SearchScreen> =
        MemoryReactiveStore(ConcurrentHashMap<String, SearchScreen>()) { searchResult ->
            "Test"
        }

    @Provides
    @Search
    fun remoteDataSource(service: SearchApiService): SearchRemoteDataSource {
        return SearchRemoteDataSource(service)
    }

    @Provides
    @Search
    fun getSearchUseCases(repository: SearchRepository): GetSearchUseCase {
        return GetSearchUseCase(repository)
    }

    @Provides
    @Search
    fun fetchSearchUseCases(repository: SearchRepository): FetchSearchUseCase {
        return FetchSearchUseCase(repository)
    }
}