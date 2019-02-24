package com.example.cansearch.search.domain

import com.example.cansearch.core.domain.GetUseCase
import com.example.cansearch.search.data.SearchRemoteData
import com.example.cansearch.search.data.SearchRepository
import io.reactivex.Observable

class GetSearchUseCase(private val repository: SearchRepository) : GetUseCase<String, SearchRemoteData> {

    override fun execute(params: String): Observable<SearchRemoteData> {
        return repository.getScreen()
    }
}