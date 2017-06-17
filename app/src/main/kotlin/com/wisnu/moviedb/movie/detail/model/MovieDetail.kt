package com.wisnu.moviedb.movie.detail.model

import java.io.Serializable

/**
 * Created by wisnu on 17/06/2017.
 */
data class MovieDetail(val posterPath: String? = "",
                       val originalTitle: String? = "",
                       val overview: String? = "",
                       val releaseDate: String? = "",
                       val voteAverage: Float? = 0f) : Serializable {

    companion object {
        val empty = MovieDetail(null, null, null, null, null)
    }

}