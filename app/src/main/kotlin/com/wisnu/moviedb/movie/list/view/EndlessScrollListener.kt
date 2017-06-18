package com.wisnu.moviedb.movie.list.view

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by wisnu on 18/06/2017.
 */

class EndlessScrollListener(private val gridLayoutManager: GridLayoutManager,
                            private val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {

    var loading = false

    companion object {
        private val VISIBLE_THRESHOLD = 5
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = gridLayoutManager.itemCount
        val lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition()
        Log.d("EndlessScrollListener", "onScrolled: " + totalItemCount)
        Log.d("EndlessScrollListener", "onScrolled: " + lastVisibleItemPosition)
        Log.d("EndlessScrollListener", "onScrolled: " + (lastVisibleItemPosition + VISIBLE_THRESHOLD))

        val endHasBeenReached = lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount
        if (!loading && totalItemCount > 0 && endHasBeenReached) {
            loading = true
            onLoadMore()
        }
    }

}

