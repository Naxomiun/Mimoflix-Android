package com.nramos.mimoflix.extension


import com.nramos.mimoflix.BR
import com.nramos.mimoflix.R
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.model.ActorViewModel
import com.nramos.mimoflix.model.MovieDetail
import com.nramos.mimoflix.model.movie.*
import com.nramos.mimoflix.model.searchedterm.SearchedTerm
import com.nramos.mimoflix.model.searchedterm.SearchedTermViewModel
import com.nramos.mimoflix.persistance.MovieDB

fun MovieDetail.mapToFavorite() = MovieDB(
    id = this.id ?: 0,
    title = this.title,
    image = this.posterImage
)

fun PopularPromoMovieViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_popular_promo_movie
)

fun PopularMovieViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_popular_movie
)

fun RecommendedMovieViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_recommended_movie
)

fun RelatedMovieViewModel.toBindingItem() = RecyclerDataBindingItem(
    item = this,
    variableId = BR.viewModel,
    layout = R.layout.item_related
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