package com.nramos.mimoflix

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.shared_appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.appbar_search -> {

                goTo<MovieDetailActivity>()
                //goTo<DetailActivity>()
            }
        }
        return true
    }

}
