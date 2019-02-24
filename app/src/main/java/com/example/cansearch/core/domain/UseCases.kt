package com.example.cansearch.core.domain

import com.example.cansearch.core.data.RemoteData
import io.reactivex.Observable

interface GetUseCase<Params, Response : Any> {
    fun execute(params: Params): Observable<Response>
}

interface FetchUseCase<Params, Error : Any> {
    fun execute(params: Params): Observable<RemoteData<Error, Nothing>>
}