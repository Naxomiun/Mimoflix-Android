package com.nramos.mimoflix.persistance

import android.content.Context
import androidx.core.content.edit

class SearchHelper(
    context: Context
) {

    private val PREFS_NAME = "searched_prefs_name"
    private val SEARCHED_PREFS_KEY = "last_searched_key"

    private val sharedPref = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveSearchedTerm(term : String) {
        val terms = sharedPref.getStringSet(SEARCHED_PREFS_KEY, emptySet())
        var auxSet : MutableSet<String> = mutableSetOf()
        terms?.let {
            auxSet = terms.toMutableSet()
        }
        auxSet.add(term)
        sharedPref.edit {
            putStringSet(SEARCHED_PREFS_KEY, auxSet)
        }
    }

    fun getLastSearchedTerms() : List<String> {
        return sharedPref.getStringSet(SEARCHED_PREFS_KEY, emptySet())?.toList() ?: emptyList()
    }

}