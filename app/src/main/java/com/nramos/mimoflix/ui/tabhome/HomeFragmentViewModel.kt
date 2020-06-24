package com.nramos.mimoflix.ui.tabhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.api.LocalProvider
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.PopularPromoMovie
import com.nramos.mimoflix.model.localgenre.LocalGenreViewModel
import com.nramos.mimoflix.model.movie.PopularPromoMovieViewModel
import com.nramos.mimoflix.repo.actors.ActorRepository
import com.nramos.mimoflix.repo.movies.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel(
    private val localProvider: LocalProvider,
    private val movieRepository: MovieRepository,
    private val actorRepository: ActorRepository
) : ViewModel() {

    private val _recommendedMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val recommendedMovies: LiveData<List<RecyclerDataBindingItem>> get() = _recommendedMovies

    private val _genres = MutableLiveData<List<RecyclerDataBindingItem>>()
    val genres: LiveData<List<RecyclerDataBindingItem>> get() = _genres

    init {
        getPopularRecommendedMovies()
        getGenres()
    }

    private fun getPopularRecommendedMovies() {
        viewModelScope.launch {
            _recommendedMovies.value = withContext(Dispatchers.IO) {
                localProvider.getPopularPromoMovies.map {
                    PopularPromoMovieViewModel(it) { movie, view ->

                    }.toBindingItem()
                }
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            _genres.value = withContext(Dispatchers.IO) {
                localProvider.getLocalGenres.map {
                    LocalGenreViewModel(it) {

                    }.toBindingItem()
                }
            }
        }
    }

}