package com.nramos.mimoflix.ui.moviedetail


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.nramos.mimoflix.databinding.ActivityDetailBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.extension.setTranslucentActivity
import com.nramos.mimoflix.ui.favoritedialog.FavoriteDialogFragment
import com.nramos.mimoflix.ui.trailer.TrailerActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

private const val FAVORITE_FRAGMENT_TAG = "added_to_favorite"

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel : MovieDetailActivityViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentActivity()
        supportPostponeEnterTransition()
        binding = ActivityDetailBinding.inflate(layoutInflater).also {
            viewModel.movieId = intent.getIntExtra("movie", 0)
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)

        with(viewModel){
            observe(actorDetailAction) {
                Log.e("DETAIL ACTIVITY", it.toString())
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

        setScrollListener()

        viewModel.getMovieDetail()
        viewModel.getRelatedMovies()
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

}
