package com.example.cansearch.search.data

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface SearchApiService {
    @GET("clinical-trials?size=50")
    fun getTransactions(): Single<SearchResult>
}