package com.example.cansearch.search.data

import com.example.cansearch.core.data.RemoteData
import com.example.cansearch.core.domain.*
import com.example.cansearch.search.domain.SearchScreen
import io.reactivex.Maybe
import io.reactivex.Observable

typealias SearchRemoteData = RemoteData<RemoteError, SearchScreen>

class SearchRepository(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val memoryStore: ReactiveStore<String, SearchScreen>
) {
    fun getScreen(test: String = "Test"): Observable<SearchRemoteData> =
        memoryStore.get(test).syncIfEmpty(fetchScreen(test))

    fun fetchScreen(test: String = "Test"): Maybe<RemoteError> =
        searchRemoteDataSource.requestSearchScreen()
            .retry(2)
            .doOnSuccess { result ->
                when (result) {
                    is Result.Success -> memoryStore.store(result.data)
                }
            }
            .flatMapMaybe { results: Result<RemoteError, SearchScreen> -> results.toMaybeError() }
}