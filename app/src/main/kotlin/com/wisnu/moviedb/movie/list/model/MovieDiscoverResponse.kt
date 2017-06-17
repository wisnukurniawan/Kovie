package com.wisnu.moviedb.movie.list.model

import java.io.Serializable

/**
 * Created by wisnu on 13/06/2017.
 */
data class MovieDiscoverResponse(val page: String? = "",
                                 val results: MutableList<MovieDiscover> = mutableListOf(),
                                 val totalPages: String? = "") : Serializable {

    companion object {
        val empty = MovieDiscoverResponse(null, mutableListOf(), null)
    }

}