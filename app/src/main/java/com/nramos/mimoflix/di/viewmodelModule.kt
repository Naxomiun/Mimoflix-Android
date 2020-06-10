package com.nramos.mimoflix.di

import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivityViewModel
import com.nramos.mimoflix.ui.trailer.TrailerActivity
import com.nramos.mimoflix.ui.trailer.TrailerActivityViewModel
import com.nramos.mimoflix.ui.genres.FragmentGenres
import com.nramos.mimoflix.ui.genres.FragmentGenresViewModel
import com.nramos.mimoflix.ui.search.SearchActivity
import com.nramos.mimoflix.ui.search.SearchActivityViewModel
import com.nramos.mimoflix.ui.tabmovies.MoviesFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    scope<FragmentGenres> {
        viewModel { FragmentGenresViewModel(get()) }
    }

    scope<MovieDetailActivity> {
        viewModel {
            MovieDetailActivityViewModel(
                get()
            )
        }
    }

    scope<TrailerActivity> {
        viewModel { TrailerActivityViewModel() }
    }

    scope<SearchActivity> {
        viewModel { SearchActivityViewModel( get(), get() ) }
    }

    viewModel {
        MoviesFragmentViewModel(get())
    }

}