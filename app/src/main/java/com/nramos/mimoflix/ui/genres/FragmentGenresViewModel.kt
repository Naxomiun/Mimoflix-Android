package com.nramos.mimoflix.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.model.Genre
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.Serie
import com.nramos.mimoflix.repo.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentGenresViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var selectedCategory : Int = 0

    private val _genres = MutableLiveData<List<Genre>>()
    val genres : LiveData<List<Genre>> get() = _genres

    private val _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>> get() = _movies

    private val _series = MutableLiveData<List<Serie>>()
    val series : LiveData<List<Serie>> get() = _series

    fun getRecommendedMedia() {
        viewModelScope.launch {

        }
    }

    fun getGenres() {
        viewModelScope.launch {
            _genres.value = withContext(Dispatchers.IO) { moviesRepository.getGenres() }
        }
    }

    fun getMediaPerGenre(genre : Int?) {
        viewModelScope.launch {
            //_movies.value = withContext(Dispatchers.IO) { moviesRepository.getMoviesPerGenre(genre ?: 0) }
            _series.value = withContext(Dispatchers.IO) { moviesRepository.getSeriesPerGenre(genre ?: 0) }
        }
    }


}