package com.example.cansearch.core.domain

import com.example.cansearch.core.data.RemoteData

sealed class Option<out A : Any> {

    object None : Option<Nothing>()
    data class Some<out A : Any>(val value: A) : Option<A>()

    inline fun <B : Any> map(mapper: (A) -> B): Option<B> = flatMap { Some(mapper(it)) }

    inline fun <B : Any> flatMap(mapper: (A) -> Option<B>): Option<B> = when (this) {
        is None -> this
        is Some -> mapper(value)
    }

    inline fun <B : Any, C : Any> combineWith(other: Option<B>, mapper: (A, B) -> C): Option<C> =
        flatMap { a -> other.map { b -> mapper(a, b) } }

    inline fun filter(predicate: (A) -> Boolean): Option<A> =
        flatMap { if (predicate(it)) Some(it) else None }

    fun isSome(): Boolean = this is Some

    fun isNone(): Boolean = this is None
}

fun <A : Any> A?.toOption(): Option<A> = if (this == null) Option.None else Option.Some(this)

fun <A : Any> Option<A>.withDefault(default: A): A = when (this) {
    is Option.None -> default
    is Option.Some -> value
}

fun <E : Any, A : Any> Option<A>.toRemoteData(emptyValue: RemoteData<E, A> = RemoteData.Loading): RemoteData<E, A> =
    when (this) {
        is Option.None -> emptyValue
        is Option.Some -> RemoteData.succeed(value)
    }