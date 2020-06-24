package com.nramos.mimoflix.ui.actordetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nramos.mimoflix.databinding.ActivityActorBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.extension.setTranslucentActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ActorDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActorBinding
    private val viewModel : ActorDetailActivityViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentActivity()
        supportPostponeEnterTransition()
        binding = ActivityActorBinding.inflate(layoutInflater).also {
            viewModel.actorId = intent.getIntExtra("actorId", 0)
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        setContentView(binding.root)

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

        viewModel.getActorDetail()
        viewModel.getActorMovies()
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
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
