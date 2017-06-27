package com.wisnu.moviedb.movie.list.model

import android.support.annotation.IntDef
import android.support.annotation.StringDef

/**
 * Created by wisnu on 17/06/2017.
 */
class MovieSorting {

    companion object {
        const val BY_POPULARITY = "popularity.desc"
        const val BY_HIGHEST_RATED = "vote_average.desc"

        const val SORT_BY_POPULARITY_POSITION = 0
        const val SORT_BY_HIGHEST_RATED_POSITION = 1
    }

    @StringDef(BY_POPULARITY, BY_HIGHEST_RATED)
    @IntDef(SORT_BY_POPULARITY_POSITION.toLong(), SORT_BY_HIGHEST_RATED_POSITION.toLong())
    @Retention(AnnotationRetention.SOURCE)
    annotation class Def

}