package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TrailerApiResponse(
    @SerializedName("results")  val results : List<Trailer>
)
