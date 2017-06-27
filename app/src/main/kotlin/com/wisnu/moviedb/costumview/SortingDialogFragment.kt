package com.wisnu.moviedb.costumview

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.wisnu.moviedb.R
import com.wisnu.moviedb.base.model.SortingEvent
import com.wisnu.moviedb.base.utils.RxBus
import com.wisnu.moviedb.di.MovieApplication
import com.wisnu.moviedb.movie.list.model.MovieSorting

/**
 * Created by wisnu on 27/06/2017.
 */
class SortingDialogFragment : DialogFragment() {

    companion object {
        val TAG: String = SortingDialogFragment::class.java.simpleName
    }

    private val preference = MovieApplication.movieComponent.providePreferenceManager()
    private var sortState = MovieSorting.SORT_BY_POPULARITY_POSITION

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context, R.style.DialogStyle)
            .setTitle(getString(R.string.sort_dialog_title))
            .setNegativeButton(getString(R.string.action_cancel), null)
            .setSingleChoiceItems(
                R.array.pref_sort_by_labels,
                getSortStateByPosition(),
                { dialog, position ->

                    saveSortStateByPosition(position)
                    publishSortingEvent()
                    dialog.dismiss()
                }).create()

    }

    private fun saveSortStateByPosition(position: Int) {
        if (position == MovieSorting.SORT_BY_POPULARITY_POSITION) {
            preference.saveSortState(MovieSorting.BY_POPULARITY)
        } else if (position == MovieSorting.SORT_BY_HIGHEST_RATED_POSITION) {
            preference.saveSortState(MovieSorting.BY_HIGHEST_RATED)
        }
    }

    private fun getSortStateByPosition(): Int {
        if (preference.getSortState() == MovieSorting.BY_POPULARITY) {
            sortState = MovieSorting.SORT_BY_POPULARITY_POSITION
        } else if (preference.getSortState() == MovieSorting.BY_HIGHEST_RATED) {
            sortState = MovieSorting.SORT_BY_HIGHEST_RATED_POSITION
        }
        return sortState
    }

    private fun publishSortingEvent() {
        RxBus.post(SortingEvent())
    }

}