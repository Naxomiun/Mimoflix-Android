package com.nramos.mimoflix.di

import com.nramos.mimoflix.model.localcompany.LocalCompany
import com.nramos.mimoflix.model.localgenre.LocalGenre
import com.nramos.mimoflix.ui.actordetail.ActorDetailActivity
import com.nramos.mimoflix.ui.actordetail.ActorDetailActivityViewModel
import com.nramos.mimoflix.ui.companylist.MoviesCompanyActivity
import com.nramos.mimoflix.ui.companylist.MoviesCompanyActivityViewModel
import com.nramos.mimoflix.ui.favoriteactivity.FavoriteActivity
import com.nramos.mimoflix.ui.favoriteactivity.FavoriteActivityViewModel
import com.nramos.mimoflix.ui.genrelist.MoviesGenreActivity
import com.nramos.mimoflix.ui.genrelist.MoviesGenreActivityViewModel
import com.nramos.mimoflix.ui.mainactivity.MainActivity
import com.nramos.mimoflix.ui.mainactivity.MainActivityViewModel
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivityViewModel
import com.nramos.mimoflix.ui.trailer.TrailerActivity
import com.nramos.mimoflix.ui.trailer.TrailerActivityViewModel
import com.nramos.mimoflix.ui.search.SearchActivity
import com.nramos.mimoflix.ui.search.SearchActivityViewModel
import com.nramos.mimoflix.ui.tabhome.HomeFragment
import com.nramos.mimoflix.ui.tabhome.HomeFragmentViewModel
import com.nramos.mimoflix.ui.tabmovies.MoviesFragmentViewModel
import com.nramos.mimoflix.ui.tabprofile.ProfileFragment
import com.nramos.mimoflix.ui.tabprofile.ProfileFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    scope<MainActivity> {
        viewModel { MainActivityViewModel() }
    }

    scope<MovieDetailActivity> {
        viewModel { (movieId : Int) -> MovieDetailActivityViewModel(get(), get(), movieId) }
    }

    scope<ActorDetailActivity> {
        viewModel { (actorId : Int) -> ActorDetailActivityViewModel(get(), get(), actorId) }
    }

    scope<TrailerActivity> {
        viewModel { TrailerActivityViewModel() }
    }

    scope<FavoriteActivity> {
        viewModel { FavoriteActivityViewModel(get()) }
    }

    scope<SearchActivity> {
        viewModel { SearchActivityViewModel(get(), get()) }
    }

    scope<MoviesGenreActivity> {
        viewModel { (genre : LocalGenre) -> MoviesGenreActivityViewModel(get(), genre) }
    }

    scope<MoviesCompanyActivity> {
        viewModel { (company : LocalCompany) -> MoviesCompanyActivityViewModel(get(), company) }
    }

    scope<HomeFragment> {
        viewModel { HomeFragmentViewModel(get()) }
    }

    scope<ProfileFragment> {
        viewModel { ProfileFragmentViewModel(get()) }
    }

    viewModel {
        MoviesFragmentViewModel(get())
    }

}