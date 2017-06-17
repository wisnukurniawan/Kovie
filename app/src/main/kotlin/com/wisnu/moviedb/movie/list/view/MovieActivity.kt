package com.wisnu.moviedb.movie.list.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.wisnu.moviedb.R
import com.wisnu.moviedb.di.MovieApplication
import com.wisnu.moviedb.movie.detail.view.ItemOffsetDecoration
import com.wisnu.moviedb.movie.detail.view.MovieDetailActivity
import com.wisnu.moviedb.movie.list.model.MovieDiscover
import com.wisnu.moviedb.movie.list.model.MovieSorting
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieActivity : AppCompatActivity() {

    private val TAG = MovieActivity::class.java.simpleName
    private val movieListPresenter by lazy { MovieApplication.movieComponent.provideMovieListPresenter() }
    private var recentSorting = MovieSorting.BY_POPULARITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initMoviesLayout()
        showPopularMovies()
    }

    private fun initMoviesLayout() {
        movies_grid.addItemDecoration(ItemOffsetDecoration(resources.getDimensionPixelOffset(R.dimen.movie_item_offset)))
        movies_grid.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.movies_columns))
    }

    private fun showPopularMovies() {
        Observable.just(true)
            .observeOn(Schedulers.io())
            .flatMap {
                movieListPresenter.retrieveDiscoverMovies(MovieSorting.BY_POPULARITY)
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorResumeNext(Function { Observable.just(null) })
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(TAG, "doOnNext showPopularMovies")

                    movies_grid.adapter = MoviesAdapter(it.results, { onItemMovieClicked(it) })
                },
                { Log.d(TAG, "onError showPopularMovies") },
                { Log.d(TAG, "onComplete showPopularMovies") }
            )
    }

    private fun showHighestRatedMovies() {
        Observable.just(true)
            .observeOn(Schedulers.io())
            .flatMap {
                movieListPresenter.retrieveDiscoverMovies(MovieSorting.BY_HIGHEST_RATED)
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorResumeNext(Function { Observable.just(null) })
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(TAG, "doOnNext showHighestRatedMovies")

                    movies_grid.adapter = MoviesAdapter(it.results, { onItemMovieClicked(it) })
                },
                { Log.d(TAG, "onError showHighestRatedMovies") },
                { Log.d(TAG, "onComplete showHighestRatedMovies") }
            )
    }

    private fun onItemMovieClicked(movieDiscover: MovieDiscover) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID, movieDiscover)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.sort_by_popularity -> {
                showPopularMovies()
                recentSorting = MovieSorting.BY_POPULARITY
            }

            R.id.sort_highest_rated -> {
                showHighestRatedMovies()
                recentSorting = MovieSorting.BY_HIGHEST_RATED
            }

            R.id.refresh_movie -> {
                refreshMovie()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun refreshMovie() {
        Observable.just(true)
            .observeOn(Schedulers.io())
            .flatMap {
                movieListPresenter.retrieveDiscoverMovies(recentSorting)
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorResumeNext(Function { Observable.just(null) })
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(TAG, "doOnNext refreshMovie")

                    movies_grid.adapter = MoviesAdapter(it.results, { onItemMovieClicked(it) })
                },
                { Log.d(TAG, "onError refreshMovie") },
                { Log.d(TAG, "onComplete refreshMovie") }
            )
    }
}
