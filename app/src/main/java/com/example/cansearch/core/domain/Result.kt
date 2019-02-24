package com.example.cansearch.core.domain

import io.reactivex.Maybe

/**
 * The result of either a [Result.Success] or [Result.Error]
 */
sealed class Result<out E : Any, out T : Any> {

    data class Success<out E : Any, out T : Any>(val data: T) : Result<E, T>()
    data class Error<out E : Any, out T : Any>(val error: E) : Result<E, T>()

    inline fun <A : Any> map(mapFunction: (T) -> A): Result<E, A> {
        return when (this) {
            is Success -> Success(mapFunction(data))
            is Error -> Error(error)
        }
    }

    inline fun <F : Any> mapError(transform: (E) -> F): Result<F, T> {
        return when (this) {
            is Success -> Success(data)
            is Error -> Error(transform(error))
        }
    }

    companion object {
        fun <A : Any> succeed(data: A): Result<Nothing, A> = Result.Success(data)

        fun <E : Any> fail(error: E): Result<E, Nothing> = Result.Error(error)
    }
}

inline fun <E : Any, A : Any, B : Any> Result<E, A>.flatMap(mapFunction: (A) -> Result<E, B>): Result<E, B> {
    return when (this) {
        is Result.Success -> mapFunction(data)
        is Result.Error -> Result.Error(error)
    }
}

fun <E : Any, A : Any> Result<E, A>.toMaybeError(): Maybe<E> = when (this) {
    is Result.Success -> Maybe.empty()
    is Result.Error -> Maybe.just(error)
}

fun <E : Any, A : Any> Collection<Result<E, A>>.filterOutFailures() = filter { it is Result.Success }

fun <E : Any, A : Any> Collection<Result<E, A>>.getSuccessData() = filterOutFailures().map { (it as Result.Success).data }

fun <K : Any, E : Any, A : Any> Map<K, Result<E, A>>.filterOutFailures() = filterValues { it is Result.Success }

fun <K : Any, E : Any, A : Any> Map<K, Result<E, A>>.getSuccessData() = filterOutFailures().mapValues { (it.value as Result.Success).data }

fun <E : Any, A : Any> Result<E, A>.successOrNull() = when (this) {
    is Result.Success -> this.data
    else -> null
}

inline fun <A : Any> attempt(f: () -> A): Result<Throwable, A> = try {
    Result.Success(f())
} catch (e: Exception) {
    Result.Error(e)
}

inline fun <A : Any> attemptTransform(f: () -> A): Result<RemoteError, A> = attempt { f() }.mapError { ParsingError(it) }