package com.nramos.mimoflix.ui.tabmovies

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.FragmentMoviesBinding
import com.nramos.mimoflix.extension.observe
import android.util.Pair as UtilPair
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.ui.tabmovies.viewpager.FragmentMovieStateAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MoviesFragment : Fragment() {

    private val viewModel : MoviesFragmentViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentMoviesBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
            it.viewpagerMovies.offscreenPageLimit = 4
            it.viewpagerMovies.adapter = FragmentMovieStateAdapter(
                requireActivity().supportFragmentManager,
                listOf(
                    getString(R.string.fragment_movies_now_playing),
                    getString(R.string.fragment_movies_popular),
                    getString(R.string.fragment_movies_top_rated),
                    getString(R.string.fragment_movies_upcoming)
                )
            )
            it.tablayout.setupWithViewPager(it.viewpagerMovies)

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel){
            observe(movieActionEvent){
                it.getContentIfNotHandled()?.let { pair ->
                    val intent = Intent(requireContext(),  MovieDetailActivity::class.java)
                    intent.putExtra("movie", pair.first.id)
                    val options = ActivityOptions.makeSceneTransitionAnimation(requireActivity(), UtilPair(pair.second, pair.second.transitionName))
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }

}