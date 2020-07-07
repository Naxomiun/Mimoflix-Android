package com.nramos.mimoflix.ui.moviedetail

import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.ActivityDetailBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.extension.setTranslucentActivity
import com.nramos.mimoflix.ui.actordetail.ActorDetailActivity
import com.nramos.mimoflix.ui.favoritedialog.FavoriteDialogFragment
import com.nramos.mimoflix.ui.trailer.TrailerActivity
import com.nramos.mimoflix.utils.StyleManager
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

private const val FAVORITE_FRAGMENT_TAG = "added_to_favorite"

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel : MovieDetailActivityViewModel by lifecycleScope.viewModel(this) {
        parametersOf(intent.getIntExtra("movie", 0))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle()
        super.onCreate(savedInstanceState)
        setTranslucentActivity()
        supportPostponeEnterTransition()
        binding = ActivityDetailBinding.inflate(layoutInflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)

        setEvents()
        setScrollListener()
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

    private fun setEvents() {
        with(viewModel){
            observe(actorDetailAction) {
                it.getContentIfNotHandled()?.let { pair ->
                    val intent = Intent(this@MovieDetailActivity,  ActorDetailActivity::class.java)
                    intent.putExtra("actorId", pair.first)
                    val options = ActivityOptions.makeSceneTransitionAnimation(this@MovieDetailActivity, android.util.Pair(pair.second, pair.second.transitionName))
                    startActivity(intent, options.toBundle())
                }
            }
            observe(trailerAction) {
                it.getContentIfNotHandled()?.let { trailer ->
                    goTo<TrailerActivity>(Pair("trailerId", trailer.trailerId))
                }
            }
            observe(bookmarkAction){
                it.getContentIfNotHandled()?.let { added ->
                    if(added) {
                        viewModel.movie.value.let {movieDetail ->
                            val favoriteDialogFragment: FavoriteDialogFragment = FavoriteDialogFragment.newInstance(movieDetail?.title ?: "")
                            favoriteDialogFragment.show(supportFragmentManager, FAVORITE_FRAGMENT_TAG)
                        }
                    }
                }
            }
            observe(backAction) {
                if(it) {
                    onBackPressed()
                }
            }
            observe(movie){
                it?.let{
                    supportStartPostponedEnterTransition()
                }
            }
        }
    }

    private fun setScrollListener() {
        binding.detailScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY > 800) {
                binding.flDetailToolbar.visibility = View.VISIBLE
            } else {
                binding.flDetailToolbar.visibility = View.GONE
            }
        })
    }

    private fun setStyle() {
        when(StyleManager(context = this).getMode())  {
            true -> setTheme(R.style.DetailActivity_Dark)
            false -> setTheme(R.style.DetailActivity_Light)
        }
    }

}
