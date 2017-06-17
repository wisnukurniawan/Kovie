package com.wisnu.moviedb.movie.detail.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wisnu.moviedb.R
import com.wisnu.moviedb.base.model.MoviePosterSize
import com.wisnu.moviedb.movie.list.model.MovieDiscover
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * Created by wisnu on 17/06/2017.
 */
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie_id"

        private const val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    }

    private val movieDiscover: MovieDiscover? by lazy { intent.getSerializableExtra(MOVIE_ID) as MovieDiscover }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        showImageMovie()
        original_title_tv.text = movieDiscover?.originalTitle
        overview_tv.text = movieDiscover?.overview
        vote_average_tv.text = movieDiscover?.voteAverage.toString()
        release_date_tv.text = movieDiscover?.releaseDate
    }

    private fun showImageMovie() {
        Glide
            .with(this)
            .load(POSTER_IMAGE_BASE_URL + MoviePosterSize.SIZE_4 + movieDiscover?.posterPath)
            .placeholder(ColorDrawable(ContextCompat.getColor(this, R.color.colorAccent)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(poster_path_iv)
    }

}