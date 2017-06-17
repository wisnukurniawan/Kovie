package com.wisnu.moviedb.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by wisnu on 13/06/2017.
 */
@Module
class MovieApplicationModule(val application: MovieApplication) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return application
    }

}