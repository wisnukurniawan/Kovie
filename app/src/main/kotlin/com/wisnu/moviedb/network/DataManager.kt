package com.wisnu.moviedb.network

import com.wisnu.moviedb.movie.list.model.MovieDiscoverResponse
import com.wisnu.moviedb.network.model.ApiClient
import com.wisnu.moviedb.network.model.UnknownError
import io.reactivex.Observable
import io.reactivex.functions.Function


/**
 * Created by wisnu on 13/06/2017.
 */
class DataManager(private val apiClient: ApiClient) {

    fun retrieveDiscoverMovies(sortBy: String, page: Int?): Observable<MovieDiscoverResponse> {
        return handleServerRequestError(apiClient.retrieveDiscoverMovies(sortBy, page))
    }

    private fun <T> handleServerRequestError(originalObservable: Observable<T>): Observable<T> {
        return originalObservable.onErrorResumeNext(Function <Throwable, Observable<T>> {
            Observable.error(UnknownError(it))
        })
    }

}