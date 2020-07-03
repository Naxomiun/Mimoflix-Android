package com.nramos.mimoflix.model.company

import java.io.Serializable

data class LocalCompany(
    val id : Int,
    val logo : Int,
    val name : String
) : Serializable