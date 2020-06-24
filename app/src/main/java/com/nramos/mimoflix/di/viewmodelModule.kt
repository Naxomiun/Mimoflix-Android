package com.nramos.mimoflix.di

import com.nramos.mimoflix.ui.actordetail.ActorDetailActivity
import com.nramos.mimoflix.ui.actordetail.ActorDetailActivityViewModel
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivityViewModel
import com.nramos.mimoflix.ui.trailer.TrailerActivity
import com.nramos.mimoflix.ui.trailer.TrailerActivityViewModel
import com.nramos.mimoflix.ui.search.SearchActivity
import com.nramos.mimoflix.ui.search.SearchActivityViewModel
import com.nramos.mimoflix.ui.tabhome.HomeFragment
import com.nramos.mimoflix.ui.tabhome.HomeFragmentViewModel
import com.nramos.mimoflix.ui.tabmovies.MoviesFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    scope<MovieDetailActivity> {
        viewModel { MovieDetailActivityViewModel(get()) }
    }

    scope<ActorDetailActivity> {
        viewModel { ActorDetailActivityViewModel(get()) }
    }

    scope<TrailerActivity> {
        viewModel { TrailerActivityViewModel() }
    }

    scope<SearchActivity> {
        viewModel { SearchActivityViewModel(get(), get()) }
    }

    scope<HomeFragment> {
        viewModel { HomeFragmentViewModel(get(), get(), get()) }
    }

    viewModel {
        MoviesFragmentViewModel(get())
    }

}