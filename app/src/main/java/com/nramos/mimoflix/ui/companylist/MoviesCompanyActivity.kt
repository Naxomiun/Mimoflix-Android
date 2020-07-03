package com.nramos.mimoflix.ui.companylist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import androidx.appcompat.app.AppCompatActivity
import com.nramos.mimoflix.databinding.ActivityMoviesCompanyListBinding
import com.nramos.mimoflix.databinding.ActivityMoviesGenreListBinding
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.model.company.LocalCompany
import com.nramos.mimoflix.model.localgenre.LocalGenre
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MoviesCompanyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesCompanyListBinding
    private val viewModel : MoviesCompanyActivityViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesCompanyListBinding.inflate(layoutInflater).also {
            viewModel.company = intent.getSerializableExtra("company") as LocalCompany
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)
        setSupportActionBar(binding.companyListToolbar)
        title = viewModel.company.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getMoviesByCompany()

        setEvents()
    }

    private fun setEvents() {
        with(viewModel) {
            observe(movieActionEvent) {
                it.getContentIfNotHandled()?.let {pair ->
                    val intent = Intent(this@MoviesCompanyActivity,  MovieDetailActivity::class.java)
                    intent.putExtra("movie", pair.first.id)
                    val options = ActivityOptions.makeSceneTransitionAnimation(this@MoviesCompanyActivity, Pair(pair.second, pair.second.transitionName))
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }

}
