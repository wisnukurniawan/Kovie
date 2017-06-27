package com.wisnu.moviedb.di

import android.content.Context
import com.wisnu.moviedb.local.PreferenceManagerModule
import com.wisnu.moviedb.local.preference.PreferenceManager
import com.wisnu.moviedb.movie.detail.MovieDetailPresenterModule
import com.wisnu.moviedb.movie.detail.presenter.MovieDetailPresenter
import com.wisnu.moviedb.movie.list.MovieListPresenterModule
import com.wisnu.moviedb.movie.list.presenter.MovieListPresenter
import com.wisnu.moviedb.network.DataManager
import com.wisnu.moviedb.network.DataManagerModule
import com.wisnu.moviedb.network.retrofit.RetrofitModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by wisnu on 13/06/2017.
 */
@Singleton
@Component(modules = arrayOf(
    MovieApplicationModule::class,
    RetrofitModule::class,
    DataManagerModule::class,
    MovieListPresenterModule::class,
    MovieDetailPresenterModule::class,
    PreferenceManagerModule::class
))
interface MovieComponent {

    fun provideApplicationContext(): Context
    fun provideDataManager(): DataManager
    fun provideMovieListPresenter(): MovieListPresenter
    fun provideMovieDetailPresenter(): MovieDetailPresenter
    fun providePreferenceManager(): PreferenceManager

}