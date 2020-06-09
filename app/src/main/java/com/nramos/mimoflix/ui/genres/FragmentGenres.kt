package com.nramos.mimoflix.ui.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nramos.mimoflix.databinding.FragmentGenresBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.model.Genre
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.ui.genres.adapters.VerticalGenreAdapter
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class FragmentGenres : Fragment() {

    private val mainAdapter by lazy {
        VerticalGenreAdapter(
            categoriesListener = { selectedCategory -> updateCategory(selectedCategory) },
            movieListener = { selectedMovie -> context?.goTo<MovieDetailActivity>()},
            serieListener = { selectedSerie -> context?.goTo<MovieDetailActivity>()}
        )
    }
    private var binding: FragmentGenresBinding? = null
    private val viewModel : FragmentGenresViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGenresBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel){
            observe(movies) {
                mainAdapter.movies = it
            }
            observe(series) {
                mainAdapter.series = it
            }
            observe(genres) {
                val list = mutableListOf(Genre(0, "Recomendados"))
                list.addAll(it)
                mainAdapter.categories = list
            }
        }

        binding?.rvGenres?.adapter = mainAdapter
        mainAdapter.category = viewModel.selectedCategory
        viewModel.getGenres()
        viewModel.getMediaPerGenre(viewModel.selectedCategory)
    }

    private fun updateCategory(genre : Int?) {
        viewModel.selectedCategory = genre ?: 0
        mainAdapter.category = genre ?: 0
        viewModel.getMediaPerGenre(genre)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}