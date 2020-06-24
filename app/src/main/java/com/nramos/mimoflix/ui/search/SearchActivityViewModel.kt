package com.nramos.mimoflix.ui.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.movie.RoundedPosterViewModel
import com.nramos.mimoflix.model.movie.SearchMovieViewModel
import com.nramos.mimoflix.model.searchedterm.SearchedTerm
import com.nramos.mimoflix.model.searchedterm.SearchedTermViewModel
import com.nramos.mimoflix.persistance.SearchHelper
import com.nramos.mimoflix.repo.movies.MovieRepository
import com.nramos.mimoflix.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivityViewModel (
    private val searchHelper: SearchHelper,
    private val movieRepository: MovieRepository
) : ViewModel() {

    val saveSearchedTerm: Function1<String, Unit> = this::saveSearchedTerm

    private val _currentSearchQuery = MutableLiveData<String>()
    val currentSearchQuery: LiveData<String> get() = _currentSearchQuery

    private val _lastSearchTerms = MutableLiveData<List<RecyclerDataBindingItem>>()
    val lastSearchTerms: LiveData<List<RecyclerDataBindingItem>> get() = _lastSearchTerms

    private val _searchedMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val searchedMovies: LiveData<List<RecyclerDataBindingItem>> get() = _searchedMovies

    private val _isSearching =  MutableLiveData<Boolean>()
    val isSearching: LiveData<Boolean> get() = _isSearching

    private val _movieActionEvent = MutableLiveData<SingleEvent<Pair<Movie, View>>>()
    val movieActionEvent : LiveData<SingleEvent<Pair<Movie, View>>> get() = _movieActionEvent

    private val _backActionEvent = MutableLiveData<SingleEvent<Boolean>>()
    val backActionEvent : LiveData<SingleEvent<Boolean>> get() = _backActionEvent

    init {
        getLastSearchedTerms()
        getMostSearchedMovies()
    }

    private fun getMostSearchedMovies() {
        viewModelScope.launch {
            _isSearching.value = false
            _searchedMovies.value = withContext(Dispatchers.IO) {
                movieRepository.getTrendingMovies().map {
                    RoundedPosterViewModel(it) { movie, view ->
                        _movieActionEvent.value = SingleEvent(Pair(movie, view))
                    }.toBindingItem()
                }
            }
        }
    }

    private fun getLastSearchedTerms() {
        viewModelScope.launch {
            _lastSearchTerms.value = withContext(Dispatchers.IO) {
                searchHelper.getLastSearchedTerms().map {
                    SearchedTermViewModel(SearchedTerm(it)) { searchedTerm ->
                        searchMovies(searchedTerm.term)
                        _currentSearchQuery.value = searchedTerm.term
                    }.toBindingItem()
                }
            }
        }
    }

    private fun searchMovies(query : String) {
        viewModelScope.launch {
            _isSearching.value = true
            _searchedMovies.value = withContext(Dispatchers.IO) {
                movieRepository.searchMovies(query).map {
                    SearchMovieViewModel(it) { movie, view ->
                        _movieActionEvent.value = SingleEvent(Pair(movie, view))
                    }.toBindingItem()
                }
            }
        }
    }

    private fun saveSearchedTerm(term: String) {
        if(term.isNotBlank())
            searchHelper.saveSearchedTerm(term)
    }

    fun onBackEvent() {
        _backActionEvent.value = SingleEvent(true)
    }

    fun onSearchEvent(charSequence: CharSequence, start : Int, before : Int, count : Int) {
        viewModelScope.launch {
            _currentSearchQuery.value = charSequence.toString()
            if (count != 0) {
                searchMovies(charSequence.toString())
            } else {
                getMostSearchedMovies()
            }
        }
    }

}