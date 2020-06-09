package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class Serie(
    @SerializedName("id") val id : Int?,
    @SerializedName("popularity") val popularity : Double?,
    @SerializedName("poster_path") val posterImage : Any?,
    @SerializedName("first_air_date") val releaseDate : String?,
    @SerializedName("vote_average") val voteAverage : Double?,
    @SerializedName("name") val title : String?,
    @SerializedName("overview") val overview : String?
) : Media {
    override fun calculatePopularity() : Float{
        return (voteAverage!!/2).toFloat()
    }
}