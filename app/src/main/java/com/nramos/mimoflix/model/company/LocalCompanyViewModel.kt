package com.nramos.mimoflix.model.company

class LocalCompanyViewModel (
    val localCompany: LocalCompany,
    val listener : (LocalCompany?) -> Unit
) {
    fun onClick() {
        listener(localCompany)
    }
}