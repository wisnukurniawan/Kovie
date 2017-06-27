package com.wisnu.moviedb.movie.list

import com.wisnu.moviedb.data.network.DataManager
import com.wisnu.moviedb.movie.list.presenter.MovieListPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by wisnu on 13/06/2017.
 */
@Module
class MovieListPresenterModule {

    @Provides
    fun provideMovieListPresenter(dataManager: DataManager): MovieListPresenter {
        return MovieListPresenter(dataManager)
    }

}