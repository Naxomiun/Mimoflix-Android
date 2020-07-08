package com.nramos.mimoflix.model.localcompany

class LocalCompanyViewModel (
    val localCompany: LocalCompany,
    val listener : (LocalCompany?) -> Unit
) {
    fun onClick() {
        listener(localCompany)
    }
}