package com.nramos.mimoflix.ui.search

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.ActivityDetailBinding
import com.nramos.mimoflix.databinding.ActivitySearchBinding
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivityViewModel
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel : SearchActivityViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)

        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            observe(movieActionEvent) {
                it.getContentIfNotHandled()?.let { pair ->
                    val intent = Intent(this@SearchActivity,  MovieDetailActivity::class.java)
                    intent.putExtra("movie", pair.first.id)
                    val options = ActivityOptions.makeSceneTransitionAnimation(this@SearchActivity, Pair(pair.second, pair.second.transitionName))
                    startActivity(intent, options.toBundle())
                }
            }

            observe(backActionEvent) {
                it.getContentIfNotHandled()?.let { goBack ->
                    if(goBack)
                        onBackPressed()
                }
            }
        }
    }

}