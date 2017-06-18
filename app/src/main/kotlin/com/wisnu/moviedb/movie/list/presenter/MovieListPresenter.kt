package com.wisnu.moviedb.movie.list.presenter

import com.wisnu.moviedb.movie.list.model.MovieDiscover
import com.wisnu.moviedb.movie.list.model.MovieDiscoverResponse
import com.wisnu.moviedb.network.DataManager
import io.reactivex.Observable

/**
 * Created by wisnu on 13/06/2017.
 */
class MovieListPresenter(private val dataManager: DataManager) {

    companion object {
        private const val PAGE_SIZE = 20
        private val TAG = MovieListPresenter::class.java.simpleName
    }

    fun retrieveDiscoverMovies(sortBy: String): Observable<MovieDiscoverResponse> {
        return dataManager.retrieveDiscoverMovies(sortBy, null)
    }

    fun retrieveDiscoverMovies(sortBy: String, movies: MutableList<MovieDiscover>): Observable<MovieDiscoverResponse> {
        return dataManager.retrieveDiscoverMovies(sortBy, getCurrentPage(movies) + 1)
    }

    private fun getCurrentPage(movies: MutableList<MovieDiscover>): Int {
        val currentPage = (movies.size - 1) / PAGE_SIZE + 1
        return currentPage
    }

}