package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.nramos.mimoflix.model.movie.Movie

@Keep
data class MovieApiResponse(
    @SerializedName("page") val page : Int,
    @SerializedName( "results")  val results : List<Movie>
)