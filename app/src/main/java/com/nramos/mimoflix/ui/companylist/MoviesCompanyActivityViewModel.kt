package com.nramos.mimoflix.ui.companylist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.company.LocalCompany
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.movie.RoundedPosterViewModel
import com.nramos.mimoflix.repo.movies.MovieRepository
import com.nramos.mimoflix.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesCompanyActivityViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    lateinit var company : LocalCompany

    private val _movies = MutableLiveData<List<RecyclerDataBindingItem?>>()
    val movies : LiveData<List<RecyclerDataBindingItem?>> get() = _movies

    private val _movieActionEvent = MutableLiveData<SingleEvent<Pair<Movie, View>>>()
    val movieActionEvent : LiveData<SingleEvent<Pair<Movie, View>>> get() = _movieActionEvent

    fun getMoviesByCompany() {
        viewModelScope.launch {
            _movies.value = withContext(Dispatchers.IO) {
                movieRepository.getMoviesPerCompany(company.id).map {
                    RoundedPosterViewModel(it) { movie, view ->
                        _movieActionEvent.value = SingleEvent(Pair(movie, view))
                    }.toBindingItem()
                }
            }
        }
    }

}