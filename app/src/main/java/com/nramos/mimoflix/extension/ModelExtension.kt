package com.nramos.mimoflix.extension


import com.nramos.mimoflix.BR
import com.nramos.mimoflix.R
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.model.ActorViewModel
import com.nramos.mimoflix.model.movie.PopularMovieViewModel
import com.nramos.mimoflix.model.movie.PopularPromoMovieViewModel
import com.nramos.mimoflix.model.movie.RecommendedMovieViewModel
import com.nramos.mimoflix.model.movie.RelatedMovieViewModel

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