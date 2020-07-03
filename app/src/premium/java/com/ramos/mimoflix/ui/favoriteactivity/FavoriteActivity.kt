package com.nramos.mimoflix.ui.favoriteactivity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nramos.mimoflix.databinding.ActivityFavoriteBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel : FavoriteActivityViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarFavorites)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setEvents()
    }

    private fun setEvents() {
        with(viewModel) {
            observe(movieActionEvent) {
                it.getContentIfNotHandled()?.let { movie ->
                    goTo<MovieDetailActivity>(Pair("movie", movie))
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

}
