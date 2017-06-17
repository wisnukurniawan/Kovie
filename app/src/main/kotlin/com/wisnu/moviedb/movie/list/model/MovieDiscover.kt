package com.wisnu.moviedb.movie.list.model

import java.io.Serializable

/**
 * Created by wisnu on 13/06/2017.
 */
data class MovieDiscover(val id: Long? = 0L,
                         val posterPath: String? = "",
                         val originalTitle: String? = "",
                         val overview: String? = "",
                         val releaseDate: String? = "",
                         val voteAverage: Float? = 0f) : Serializable {

    companion object {
        val empty = MovieDiscover(null, null, null, null, null, null)
    }

}