package com.example.cansearch.search.data

import com.example.cansearch.core.data.toResult
import com.example.cansearch.core.domain.RemoteError
import com.example.cansearch.core.domain.Result
import com.example.cansearch.core.domain.flatMap
import com.example.cansearch.search.domain.SearchScreen
import io.reactivex.Single

class SearchRemoteDataSource(private val service: SearchApiService){

    fun requestSearchScreen() : Single<Result<RemoteError, SearchScreen>>{
        return service.getTrials()
            .map { response -> response.toResult() }
            .map { result: Result<RemoteError, SearchResultRaw> -> result.flatMap { it -> SearchScreen.mapFromSearchResultRaw(it) }
            }
    }
}
