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
import com.nramos.mimoflix.model.Serie

class SerieAdapter(
    private val category : Int = 0,
    private val items : List<Serie> = emptyList(),
    private val serieListener : (Int?) -> Unit
) : RecyclerView.Adapter<SerieAdapter.SerieGenreViewHolder>(){

    private var RECOMMENDED : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SerieGenreViewHolder {
        return when(category) {
            RECOMMENDED -> RecommendedSerieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre_recommended, parent, false))
            else -> SimpleSerieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre_simple, parent, false))
        }
    }

    override fun onBindViewHolder(holder: SerieGenreViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() : Int = items.size


    abstract inner class SerieGenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(serie : Serie)
    }

    inner class RecommendedSerieViewHolder(itemView: View) : SerieGenreViewHolder(itemView) {
        private val binding = ItemGenreRecommendedBinding.bind(itemView)
        override fun bind(serie : Serie) {
            binding.apply {
                ivPosterMovie.loadImageWithGlow(serie.posterImage, cvPosterMovie)
                tvTitle.text = serie.title
                rbMovie.rating = serie.calculatePopularity()
                cvPosterMovie.setOnClickListener {
                    serieListener(serie.id)
                }
            }
        }
    }

    inner class SimpleSerieViewHolder(itemView: View) : SerieGenreViewHolder(itemView)  {
        private val binding = ItemGenreSimpleBinding.bind(itemView)
        override fun bind(serie : Serie) {
            binding.apply {
                ivPosterMovie.loadAsyncImage(serie.posterImage, R.dimen.simple_genre_corners)
                ivPosterMovie.setOnClickListener {
                    serieListener(serie.id)
                }
            }
        }
    }

}