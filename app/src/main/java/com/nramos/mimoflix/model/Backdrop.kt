package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class Backdrop(
    @SerializedName("file_path") val image : String?
)