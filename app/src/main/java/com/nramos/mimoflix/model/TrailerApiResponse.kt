package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class TrailerApiResponse(
    @SerializedName("results")  val results : List<Trailer>
)
