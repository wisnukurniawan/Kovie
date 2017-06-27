package com.wisnu.moviedb.movie.detail

import com.wisnu.moviedb.data.network.DataManager
import com.wisnu.moviedb.movie.detail.presenter.MovieDetailPresenter
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