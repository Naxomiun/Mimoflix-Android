package com.nramos.mimoflix.ui.companylist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import androidx.appcompat.app.AppCompatActivity
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.ActivityMoviesCompanyListBinding
import com.nramos.mimoflix.databinding.ActivityMoviesGenreListBinding
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.model.company.LocalCompany
import com.nramos.mimoflix.model.localgenre.LocalGenre
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.utils.StyleManager
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class MoviesCompanyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesCompanyListBinding
    private val viewModel : MoviesCompanyActivityViewModel by lifecycleScope.viewModel(this) {
        parametersOf(intent.getSerializableExtra("company") as LocalCompany)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle()
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesCompanyListBinding.inflate(layoutInflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)
        setSupportActionBar(binding.companyListToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    private fun setStyle() {
        when(StyleManager(context = this).getMode())  {
            true -> setTheme(R.style.Mimoflix_Dark)
            false -> setTheme(R.style.Mimoflix_Light)
        }
    }

}
