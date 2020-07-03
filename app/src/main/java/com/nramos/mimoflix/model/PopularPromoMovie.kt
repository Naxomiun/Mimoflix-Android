package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.nramos.mimoflix.model.Media

@Keep
data class PopularPromoMovie(
    @SerializedName("id") val id : Int?,
    @SerializedName("popularity") val popularity : Double?,
    @SerializedName("poster_path") val posterImage : Int?,
    @SerializedName("release_date") val releaseDate : String?,
    @SerializedName("vote_average") val voteAverage : Double?,
    @SerializedName("title") val title : String?,
    @SerializedName("overview") val overview : String?
) : Media {
    override fun calculatePopularity() : Float{
        return (voteAverage!!/2).toFloat()
    }
}