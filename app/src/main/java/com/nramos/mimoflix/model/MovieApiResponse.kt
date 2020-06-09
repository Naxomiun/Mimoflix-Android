package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName
import com.nramos.mimoflix.model.movie.Movie

data class MovieApiResponse(
    @SerializedName("page") val page : Int,
    @SerializedName( "results")  val results : List<Movie>
)