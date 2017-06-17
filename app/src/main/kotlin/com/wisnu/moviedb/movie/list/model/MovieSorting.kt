package com.wisnu.moviedb.movie.list.model

import android.support.annotation.StringDef

/**
 * Created by wisnu on 17/06/2017.
 */
class MovieSorting {

    companion object {
        const val BY_POPULARITY = "popularity.desc"
        const val BY_HIGHEST_RATED = "vote_average.desc"
    }

    @StringDef(BY_POPULARITY, BY_HIGHEST_RATED)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Def

}