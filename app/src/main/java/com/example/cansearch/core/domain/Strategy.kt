package com.example.cansearch.core.domain

import com.example.cansearch.core.data.RemoteData
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.MaybeSubject

fun <A : Any> Observable<Option<A>>.syncIfEmpty(fetch: Maybe<RemoteError>): Observable<RemoteData<RemoteError, A>> =
syncIfEmpty(fetch) { SyncError }

inline fun <E : Any, A : Any> Observable<Option<A>>.syncIfEmpty(
    fetch: Maybe<E>,
    crossinline errorConverter: (Throwable) -> E
): Observable<RemoteData<E, A>> {
    val errorUpdater = MaybeSubject.create<RemoteData<E, A>>()

    return map { it.toRemoteData<E, A>(RemoteData.Loading) }
        .mergeWith(errorUpdater)
        .doOnNext {
            if (it.isLoading()) {
                fetch.subscribeOn(Schedulers.io()).subscribe(
                    { apiError -> errorUpdater.onSuccess(RemoteData.fail(apiError)) },
                    { ioError -> errorUpdater.onSuccess(RemoteData.fail(errorConverter(ioError))) },
                    { errorUpdater.onComplete() }
                )
            }
        }
}