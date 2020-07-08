package com.nramos.mimoflix.ui.tabhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.api.LocalProvider
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.localcompany.LocalCompany
import com.nramos.mimoflix.model.localcompany.LocalCompanyViewModel
import com.nramos.mimoflix.model.localgenre.LocalGenre
import com.nramos.mimoflix.model.localgenre.LocalGenreViewModel
import com.nramos.mimoflix.model.movie.PopularPromoMovieViewModel
import com.nramos.mimoflix.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel(
    private val localProvider: LocalProvider
) : ViewModel() {

    private val _recommendedMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val recommendedMovies: LiveData<List<RecyclerDataBindingItem>> get() = _recommendedMovies

    private val _genres = MutableLiveData<List<RecyclerDataBindingItem>>()
    val genres: LiveData<List<RecyclerDataBindingItem>> get() = _genres

    private val _companies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val companies: LiveData<List<RecyclerDataBindingItem>> get() = _companies

    private val _movieActionEvent = MutableLiveData<SingleEvent<Int>>()
    val movieActionEvent : LiveData<SingleEvent<Int>> get() = _movieActionEvent

    private val _companyActionEvent = MutableLiveData<SingleEvent<LocalCompany>>()
    val companyActionEvent : LiveData<SingleEvent<LocalCompany>> get() = _companyActionEvent

    private val _genreActionEvent = MutableLiveData<SingleEvent<LocalGenre>>()
    val genreActionEvent : LiveData<SingleEvent<LocalGenre>> get() = _genreActionEvent

    init {
        getPopularRecommendedMovies()
        getGenres()
        getCompanies()
    }

    private fun getPopularRecommendedMovies() {
        viewModelScope.launch {
            _recommendedMovies.value = withContext(Dispatchers.IO) {
                localProvider.getPopularPromoMovies().map {
                    PopularPromoMovieViewModel(it) { movie ->
                        _movieActionEvent.value = SingleEvent(movie?.id ?: 0)
                    }.toBindingItem()
                }
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            _genres.value = withContext(Dispatchers.IO) {
                localProvider.getLocalGenres().map { it ->
                    LocalGenreViewModel(it) { genre ->
                        genre?.let { it ->
                            _genreActionEvent.value = SingleEvent(it)
                        }
                    }.toBindingItem()
                }
            }
        }
    }

    private fun getCompanies() {
        viewModelScope.launch {
            _companies.value = withContext(Dispatchers.IO) {
                localProvider.getLocalCompanies().map {
                    LocalCompanyViewModel(it) { company ->
                        company?.let { it ->
                            _companyActionEvent.value = SingleEvent(it)
                        }
                    }.toBindingItem()
                }
            }
        }
    }

}