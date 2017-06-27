package com.wisnu.moviedb.movie.list.view

import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wisnu.moviedb.R
import com.wisnu.moviedb.base.model.PosterSize
import com.wisnu.moviedb.base.view.MovieImageView
import com.wisnu.moviedb.movie.list.model.MovieDiscover

/**
 * Created by wisnu on 17/06/2017.
 */
class MoviesAdapter(val movieList: MutableList<MovieDiscover>,
                    val onItemMovieClicked: (MovieDiscover) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    fun addMovieList(movieList: MutableList<MovieDiscover>, recyclerView: RecyclerView) {
        this.movieList.addAll(movieList)

        recyclerView.recycledViewPool.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder?, position: Int) {
        if (position != RecyclerView.NO_POSITION && holder != null) {
            val movie = movieList[position]

            holder.bindView(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.grid_item_movie, parent, false)
        return MoviesViewHolder(view, movieList, onItemMovieClicked)
    }

    class MoviesViewHolder(itemView: View?,
                           movieList: MutableList<MovieDiscover>,
                           onItemMovieClicked: (MovieDiscover) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val moviePoster = itemView?.findViewById(R.id.image_movie_poster) as MovieImageView
        val movieRating = itemView?.findViewById(R.id.movie_rating) as TextView
        val moviePopular = itemView?.findViewById(R.id.movie_popular) as TextView

        companion object {
            private const val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
        }

        init {
            itemView?.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val movie = movieList[adapterPosition]
                    onItemMovieClicked(movie)
                }
            }
        }

        fun bindView(movie: MovieDiscover) {
            Glide
                .with(itemView.context)
                .load(POSTER_IMAGE_BASE_URL + PosterSize.SIZE_2 + movie.posterPath)
                .placeholder(ColorDrawable(ContextCompat.getColor(itemView.context, R.color.colorAccent)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(moviePoster)

            movieRating.text = "⭐ ${movie.voteAverage}"
            moviePopular.text = "🔥 ${movie.popularity}"
        }

    }

}