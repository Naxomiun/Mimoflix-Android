package com.nramos.mimoflix.model.company

class CompanyViewModel (
    val company: Company,
    val listener : (Company?) -> Unit
) {
    fun onClick() {
        listener(company)
    }
}