package com.example.cansearch.search.domain

import com.example.cansearch.core.data.RemoteData
import com.example.cansearch.core.domain.FetchUseCase
import com.example.cansearch.core.domain.RemoteError
import com.example.cansearch.search.data.SearchRepository
import io.reactivex.Observable

// todo - use typeAlias for fetchResult
class FetchSearchUseCase(private val searchRepository: SearchRepository) : FetchUseCase<String, RemoteError> {

    override fun execute(params: String): Observable<RemoteData<RemoteError, Nothing>> {
        return searchRepository.fetchScreen()
            .toObservable()
            .map { RemoteData.fail(it) }
            .startWith(RemoteData.Loading)
    }

}