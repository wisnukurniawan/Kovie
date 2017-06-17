package com.wisnu.moviedb.movie.list.presenter

import com.wisnu.moviedb.movie.list.model.MovieDiscoverResponse
import com.wisnu.moviedb.network.DataManager
import io.reactivex.Observable

/**
 * Created by wisnu on 13/06/2017.
 */
class MovieListPresenter(private val dataManager: DataManager) {

    fun retrieveDiscoverMovies(sortBy: String): Observable<MovieDiscoverResponse> {
        return dataManager.retrieveDiscoverMovies(sortBy)
    }

}