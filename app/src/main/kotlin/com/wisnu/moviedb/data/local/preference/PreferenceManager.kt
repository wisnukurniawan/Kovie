package com.wisnu.moviedb.data.local.preference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.wisnu.moviedb.movie.list.model.MovieSorting

/**
 * Created by wisnu on 27/06/2017.
 */
class PreferenceManager(val context: Context) {

    private val KEY_SORTING = "sortBy"

    fun saveSortState(sortState: String) {
        getEditor().putString(KEY_SORTING, sortState).commit()
    }

    fun getSortState(): String {
        return getPreferences().getString(KEY_SORTING, MovieSorting.BY_POPULARITY)
    }

    private fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun getEditor(): SharedPreferences.Editor {
        return getPreferences().edit()
    }

}