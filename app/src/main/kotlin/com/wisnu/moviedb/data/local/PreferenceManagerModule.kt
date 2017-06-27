package com.wisnu.moviedb.data.local

import android.content.Context
import com.wisnu.moviedb.data.local.preference.PreferenceManager
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