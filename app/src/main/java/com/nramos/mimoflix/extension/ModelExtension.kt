package com.nramos.mimoflix.extension


import com.nramos.mimoflix.BR
import com.nramos.mimoflix.R
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.model.ActorMovieViewModel
import com.nramos.mimoflix.model.ActorViewModel
import com.nramos.mimoflix.model.MovieDetail
import com.nramos.mimoflix.model.company.LocalCompanyViewModel
import com.nramos.mimoflix.model.localgenre.LocalGenreViewModel
import com.nramos.mimoflix.model.movie.*
import com.nramos.mimoflix.model.searchedterm.SearchedTermViewModel
import com.nramos.mimoflix.model.moviedb.MovieDB
import com.nramos.mimoflix.model.moviedb.MovieDBViewModel

fun MovieDetail.mapToFavorite() = MovieDB(
    id = this.id ?: 0,
    title = this.title,
    image = this.posterImage
)

fun RoundedPosterViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_rounded_movie_poster
)

fun ActorMovieViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_rounded_movie_poster_horizontal
)

fun RelatedMovieViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_related_movie
)

fun SearchMovieViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_search_movie
)

fun ActorViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_cast_actor
)

fun SearchedTermViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_last_search_term
)

fun LocalGenreViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_genre
)

fun PopularPromoMovieViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_carousel
)

fun LocalCompanyViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_company
)

fun MovieDBViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_favorite
)