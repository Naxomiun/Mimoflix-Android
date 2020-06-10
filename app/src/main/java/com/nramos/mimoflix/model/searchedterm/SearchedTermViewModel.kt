package com.nramos.mimoflix.model.searchedterm

class SearchedTermViewModel (
    val searchedTerm: SearchedTerm,
    val listener : (SearchedTerm) -> Unit
) {
    fun onClick() {
        listener(searchedTerm)
    }
}