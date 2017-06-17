package com.wisnu.moviedb.movie.detail.view

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by wisnu on 17/06/2017.
 */
class ItemOffsetDecoration(private val itemOffset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.set(itemOffset, itemOffset, itemOffset, itemOffset)
    }
}