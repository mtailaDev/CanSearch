package com.example.cansearch.core.data

import com.example.cansearch.core.domain.HttpError
import com.example.cansearch.core.domain.RemoteError
import com.example.cansearch.core.domain.Result
import retrofit2.Response

fun <T : Any> Response<T>.toResult(): Result<RemoteError, T> {
    return if (isSuccessful) toSuccess() else toError()
}

fun <T : Any> Response<T>.toSuccess(): Result.Success<RemoteError, T> {
    return Result.Success(body()!!)
}

fun <T : Any> Response<T>.toError(): Result.Error<RemoteError, T> {
    return Result.Error(HttpError(code(), message()))
}