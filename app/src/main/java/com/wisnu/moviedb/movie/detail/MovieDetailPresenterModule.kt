package com.wisnu.moviedb.movie.detail

import com.wisnu.moviedb.movie.detail.presenter.MovieDetailPresenter
import com.wisnu.moviedb.network.DataManager
import dagger.Module
import dagger.Provides

/**
 * Created by wisnu on 17/06/2017.
 */
@Module
class MovieDetailPresenterModule {

    @Provides
    fun provideMovieDetailPresenter(dataManager: DataManager): MovieDetailPresenter {
        return MovieDetailPresenter(dataManager)
    }

}