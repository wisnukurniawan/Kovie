package com.wisnu.moviedb.di

import android.content.Context
import com.wisnu.moviedb.data.local.PreferenceManagerModule
import com.wisnu.moviedb.data.local.preference.PreferenceManager
import com.wisnu.moviedb.data.network.DataManager
import com.wisnu.moviedb.data.network.DataManagerModule
import com.wisnu.moviedb.data.network.retrofit.RetrofitModule
import com.wisnu.moviedb.movie.detail.MovieDetailPresenterModule
import com.wisnu.moviedb.movie.detail.presenter.MovieDetailPresenter
import com.wisnu.moviedb.movie.list.MovieListPresenterModule
import com.wisnu.moviedb.movie.list.presenter.MovieListPresenter
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