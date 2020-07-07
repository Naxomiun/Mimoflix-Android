package com.nramos.mimoflix.ui.actordetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.ActivityActorBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.extension.setTranslucentActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.utils.StyleManager
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class ActorDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActorBinding
    private val viewModel : ActorDetailActivityViewModel by lifecycleScope.viewModel(this) {
        parametersOf(intent.getIntExtra("actorId", 0))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle()
        super.onCreate(savedInstanceState)
        setTranslucentActivity()
        supportPostponeEnterTransition()
        binding = ActivityActorBinding.inflate(layoutInflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)

        setEvents()
    }

    private fun setEvents() {
        with(viewModel){
            observe(movieActionEvent) {
                it.getContentIfNotHandled()?.let { movie ->
                    goTo<MovieDetailActivity>(Pair("movie", movie))
                }
            }

            observe(actor){
                it?.let{
                    supportStartPostponedEnterTransition()
                }
            }
        }
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

    private fun setStyle() {
        when(StyleManager(context = this).getMode())  {
            true -> setTheme(R.style.DetailActivity_Dark)
            false -> setTheme(R.style.DetailActivity_Light)
        }
    }

    /*private fun setScrollListener() {
        binding.detailScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY > 800) {
                binding.flDetailToolbar.visibility = View.VISIBLE
            } else {
                binding.flDetailToolbar.visibility = View.GONE
            }
        })
    }*/

}
