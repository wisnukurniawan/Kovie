package com.wisnu.moviedb.movie.detail.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wisnu.moviedb.R
import com.wisnu.moviedb.base.model.PosterSize
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

        initView()
    }

    private fun initView() {
        initToolbar()
        showImageMovie()

        original_title_tv.text = movieDiscover?.originalTitle
        overview_tv.text = getOverview()
        vote_average_tv.text = getString(R.string.rating, movieDiscover?.voteAverage.toString())
        release_date_tv.text = getString(R.string.release_date, movieDiscover?.releaseDate)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar_detail.setNavigationOnClickListener { onBackPressed() }
        collapsing_toolbar_layout.title = movieDiscover?.originalTitle
        collapsing_toolbar_layout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
        title = ""
    }

    private fun showImageMovie() {
        Glide
            .with(this)
            .load(POSTER_IMAGE_BASE_URL + PosterSize.SIZE_4 + movieDiscover?.posterPath)
            .placeholder(ColorDrawable(ContextCompat.getColor(this, R.color.colorAccent)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(poster_path_iv)
    }

    private fun getOverview(): String {
        val overview = movieDiscover?.overview
        return if (overview == "") {
            "No description"
        } else {
            overview ?: ""
        }
    }

}