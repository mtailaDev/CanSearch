package com.example.cansearch.core.domain

import io.reactivex.Observable

interface ReactiveStore<K, V : Any>{

    fun store(item: V)

    fun store(item: Collection<V>)

    fun get(key: K): Observable<Option<V>>

    fun getAll(key: K): Observable<Option<Set<V>>>

    fun clear()
}