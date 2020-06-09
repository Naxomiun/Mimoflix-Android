package com.nramos.mimoflix.ui.genres.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.ItemMainGenreBinding
import com.nramos.mimoflix.databinding.ItemSelectorListBinding
import com.nramos.mimoflix.model.Genre
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.Serie
import kotlin.properties.Delegates

class VerticalGenreAdapter(
    var category : Int = 0,
    categories : List<Genre> = emptyList(),
    movies : List<Movie> = emptyList(),
    series : List<Serie> = emptyList(),
    private val categoriesListener : (Int?) -> Unit,
    private val movieListener : (Int?) -> Unit,
    private val serieListener : (Int?) -> Unit
) : RecyclerView.Adapter<VerticalGenreAdapter.MainViewHolder>() {

    private var SELECTOR_GENRE_VIEWTYPE: Int = 0
    private var MOVIE_LIST_VIEWTYPE: Int = 1
    private var SERIES_LIST_VIEWTYPE: Int = 2

    var categories: List<Genre> by Delegates.observable(categories) { _, _, _ ->
        notifyItemChanged(SELECTOR_GENRE_VIEWTYPE)
    }

    var movies: List<Movie> by Delegates.observable(movies) { _, _, _ ->
        notifyItemChanged(MOVIE_LIST_VIEWTYPE)
    }

    var series: List<Serie> by Delegates.observable(series) { _, _, _ ->
        notifyItemChanged(SERIES_LIST_VIEWTYPE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return when (viewType) {
            SELECTOR_GENRE_VIEWTYPE -> SelectorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_selector_list, parent, false))
            MOVIE_LIST_VIEWTYPE -> MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_genre, parent, false))
            else -> SerieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_genre, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> SELECTOR_GENRE_VIEWTYPE
            1 -> MOVIE_LIST_VIEWTYPE
            else -> SERIES_LIST_VIEWTYPE
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind()
    }

    abstract inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind()
    }

    inner class SelectorViewHolder(itemView: View) : MainViewHolder(itemView) {
        private val binding = ItemSelectorListBinding.bind(itemView)
        override fun bind() {
            binding.apply {
                rvSelector.apply {
                    adapter = CategorySelectorAdapter(category, categories){ categoriesListener(it) }
                }
            }
        }
    }

    inner class MovieViewHolder(itemView: View) : MainViewHolder(itemView) {
        private val binding = ItemMainGenreBinding.bind(itemView)
        override fun bind() {
            binding.apply {
                rvMain.apply {
                    setEmptyView(pbContentLoading)
                    adapter = MovieAdapter(category, movies) { movieListener(it) }
                    tvTypeTitle.text = context.getString(R.string.string_peliculas)
                }
            }
        }
    }

    inner class SerieViewHolder(itemView: View) : MainViewHolder(itemView) {
        private val binding = ItemMainGenreBinding.bind(itemView)
        override fun bind() {
            binding.apply {
                rvMain.apply {
                    setEmptyView(pbContentLoading)
                    adapter = SerieAdapter(category, series) { serieListener(it) }
                    tvTypeTitle.text = context.getString(R.string.string_series)
                }
            }
        }
    }


}