package com.nramos.mimoflix.ui.tabprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.extension.toRecentBindingItem
import com.nramos.mimoflix.model.Actor
import com.nramos.mimoflix.model.ActorViewModel
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.movie.RoundedPosterViewModel
import com.nramos.mimoflix.persistance.RecentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragmentViewModel(
    private val recentDao: RecentDao
) : ViewModel() {

    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean> get() = _loggedIn

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _profileImage = MutableLiveData<String>()
    val profileImage: LiveData<String> get() = _profileImage

    private val _recentElements = MutableLiveData<List<RecyclerDataBindingItem>>()
    val recentElements: LiveData<List<RecyclerDataBindingItem>> get() = _recentElements

    private val _totalMovies = MutableLiveData<Int>()
    val totalMovies: LiveData<Int> get() = _totalMovies

    private val _totalCast = MutableLiveData<Int>()
    val totalCast: LiveData<Int> get() = _totalCast

    fun setData(account : GoogleSignInAccount?) {
        viewModelScope.launch {
            account?.let {
                _loggedIn.value = true
                _profileImage.value = it.photoUrl.toString()
                _name.value = it.givenName
            }
            getRecentElements()
        }
    }

    private fun getRecentElements() {
        viewModelScope.launch {
            _totalMovies.value = withContext(Dispatchers.IO) {
                recentDao.getCountRecentMovies()
            }

            _totalCast.value = withContext(Dispatchers.IO) {
                recentDao.getCountRecentCast()
            }

            _recentElements.value = withContext(Dispatchers.IO) {
                recentDao.getRecentMovies().map {
                    val movie = Movie(it.id, 0.0, it.image, "", 0.0,"","")
                    RoundedPosterViewModel(movie) { movie, view ->

                    }.toBindingItem()
                } + recentDao.getRecentCast().map {
                    val cast = Actor(it.id, it.image)
                    ActorViewModel(cast) { cast, view ->

                    }.toRecentBindingItem()
                }
            }
        }
    }

}

