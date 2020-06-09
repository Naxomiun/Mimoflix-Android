package com.nramos.mimoflix.extension

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.os.bundleOf
import com.nramos.mimoflix.R
import android.util.Pair as UtilPair

inline fun <reified T : Activity> Context.goTo(vararg pairs : Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundleOf(*pairs))
    startActivity(intent)
}

inline fun <reified T : Activity> Context.transitionTo(vararg views : View) {
    val pairs = mutableListOf<UtilPair<View, String>>()
    views.forEach {
       pairs += UtilPair.create(it, it.transitionName)
    }
    val intent = Intent(this,  T::class.java)
    val options = ActivityOptions.makeSceneTransitionAnimation((this as Activity), *pairs.toTypedArray())
    startActivity(intent, options.toBundle())
}

fun Activity.setTranslucentActivity() {
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
}

fun Activity.setFullScreenActivity(){
    window.decorView.systemUiVisibility = (
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE
    )
}
