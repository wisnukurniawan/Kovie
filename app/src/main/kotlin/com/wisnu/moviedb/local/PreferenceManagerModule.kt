package com.wisnu.moviedb.local

import android.content.Context
import com.wisnu.moviedb.local.preference.PreferenceManager
import dagger.Module
import dagger.Provides

/**
 * Created by wisnu on 27/06/2017.
 */
@Module
class PreferenceManagerModule {

    @Provides
    fun providePreferenceManager(context: Context): PreferenceManager {
        return PreferenceManager(context)
    }

}