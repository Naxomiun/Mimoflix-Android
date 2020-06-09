package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class GenreApiResponse (
    @SerializedName("genres") val genres : List<Genre>?
)