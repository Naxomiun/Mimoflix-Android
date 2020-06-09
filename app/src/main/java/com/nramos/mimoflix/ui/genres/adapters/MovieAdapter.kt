package com.nramos.mimoflix.ui.genres.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.ItemGenreRecommendedBinding
import com.nramos.mimoflix.databinding.ItemGenreSimpleBinding
import com.nramos.mimoflix.extension.loadAsyncImage
import com.nramos.mimoflix.extension.loadImageWithGlow
import com.nramos.mimoflix.model.movie.Movie

class MovieAdapter(
    private val category : Int,
    private val items : List<Movie> = emptyList(),
    private val movieListener : (Int?) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieGenreViewHolder>(){

    private var RECOMMENDED : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MovieAdapter.MovieGenreViewHolder {
        return when(category) {
            RECOMMENDED -> RecommendedMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre_recommended, parent, false))
            else -> SimpleMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre_simple, parent, false))
        }
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieGenreViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() : Int = items.size

    abstract inner class MovieGenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(movie : Movie)
    }

    inner class RecommendedMovieViewHolder(itemView: View) : MovieGenreViewHolder(itemView) {
        private val binding = ItemGenreRecommendedBinding.bind(itemView)
        override fun bind(movie : Movie) {
            binding.apply {
                ivPosterMovie.loadImageWithGlow(movie.posterImage, cvPosterMovie)
                tvTitle.text = movie.title
                rbMovie.rating = movie.calculatePopularity()
                cvPosterMovie.setOnClickListener {
                    movieListener(movie.id)
                }
            }
        }
    }

    inner class SimpleMovieViewHolder(itemView: View) : MovieGenreViewHolder(itemView)  {
        private val binding = ItemGenreSimpleBinding.bind(itemView)
        override fun bind(movie : Movie) {
            binding.apply {
                ivPosterMovie.loadAsyncImage(movie.posterImage, R.dimen.simple_genre_corners)
                ivPosterMovie.setOnClickListener {
                    movieListener(movie.id)
                }
            }
        }
    }

}



