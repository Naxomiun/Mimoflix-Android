package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieGenre (
    @SerializedName("id") val id : Int?,
    @SerializedName("name") val name : String?
)
