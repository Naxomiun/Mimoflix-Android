package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName
import com.nramos.mimoflix.extension.minsToStringFormat

data class MovieDetail(
    @SerializedName("id") val id : Int?,
    @SerializedName("genres") val genres : List<MovieGenre>?,
    @SerializedName("overview") val synopsis : String?,
    @SerializedName("poster_path") val posterImage : String?,
    @SerializedName("backdrop_path") val backdropImage : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("release_date") val releaseDate : String?,
    @SerializedName("vote_average") val voteAverage : Double?,
    @SerializedName("credits") val credits : Credits?,
    @SerializedName("runtime") val length : Int?
) {
    fun calculatePopularity() : String?{
        return String.format("%.2f", (voteAverage?.div(2)?.toFloat()))
    }

    fun calculateLength() : String?{
        return  String.format("%s", (length?.minsToStringFormat()))
    }
}