package com.wisnu.moviedb.di

import android.app.Application

/**
 * Created by wisnu on 13/06/2017.
 */
class MovieApplication : Application() {

    companion object {
        lateinit var movieComponent: MovieComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        movieComponent =
            DaggerMovieComponent
                .builder()
                .movieApplicationModule(MovieApplicationModule(this))
                .build()
    }

}