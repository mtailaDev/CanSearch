package com.example.cansearch.search.data

import com.example.cansearch.core.domain.ReactiveStore

class SearchRepository(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val memoryStore: ReactiveStore<String, SearchResultRaw>
)