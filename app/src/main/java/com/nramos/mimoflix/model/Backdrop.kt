package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Backdrop(
    @SerializedName("file_path") val image : String?
)