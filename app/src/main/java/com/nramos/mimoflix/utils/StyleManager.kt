package com.nramos.mimoflix.utils

import android.content.Context
import android.graphics.Paint
import androidx.core.content.edit

class StyleManager(
    context : Context
) {
    private val PREFS_NAME = "style_prefs_name"
    private val PREFS_KEY = "style_prefs_key"
    private val PREFS_RECREATE_KEY = "recreate_prefs_key"

    private val sharedPref = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveMode(isDark : Boolean) {
        sharedPref.edit {
            putBoolean(PREFS_KEY, isDark)
        }
    }

    fun getMode() : Boolean {
       return sharedPref.getBoolean(PREFS_KEY, false)
    }

    fun recreate(should : Boolean) {
        sharedPref.edit {
            putBoolean(PREFS_RECREATE_KEY, should)
        }
    }

    fun shouldRecreate() : Boolean {
        return sharedPref.getBoolean(PREFS_RECREATE_KEY, false)
    }

}