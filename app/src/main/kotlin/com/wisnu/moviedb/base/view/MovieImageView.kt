package com.wisnu.moviedb.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by wisnu on 17/06/2017.
 */
class MovieImageView(context: Context, attrs: AttributeSet?) : ImageView(context, attrs) {

    companion object {
        private const val ASPECT_RATIO = 1.5f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = Math.round(width * ASPECT_RATIO)
        setMeasuredDimension(width, height)
    }
}