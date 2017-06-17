package com.wisnu.moviedb.network.model

import com.wisnu.moviedb.movie.detail.model.MovieDetail
import com.wisnu.moviedb.movie.list.model.MovieDiscoverResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by wisnu on 13/06/2017.
 */
interface ApiClient {

    @GET("discover/movie?language=id")
    fun retrieveDiscoverMovies(@Query("sort_by") sortBy: String): Observable<MovieDiscoverResponse>

    @GET("movie/{id}?language=id")
    fun retrieveDetailMovie(@Path("id") movieId: Long): Observable<MovieDetail>

}