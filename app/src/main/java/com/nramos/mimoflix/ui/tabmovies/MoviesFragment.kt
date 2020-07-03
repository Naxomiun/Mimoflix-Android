package com.nramos.mimoflix.ui.tabmovies

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nramos.mimoflix.R
import com.nramos.mimoflix.ui.search.SearchActivity
import com.nramos.mimoflix.databinding.FragmentMoviesBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.ui.favoriteactivity.FavoriteActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import com.nramos.mimoflix.ui.tabmovies.viewpager.FragmentMovieStateAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import android.util.Pair as UtilPair

class MoviesFragment : Fragment() {

    private val viewModel : MoviesFragmentViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentMoviesBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
            setupToolbar(it)
            setupViewPager(it)
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

    private fun setupToolbar(binding : FragmentMoviesBinding) {
        binding.toolbarFragmentMovies.inflateMenu(R.menu.shared_appbar_menu)
        binding.toolbarFragmentMovies.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.menu_action_search -> {
                    context?.goTo<SearchActivity>()
                }
                R.id.menu_action_favorite -> {
                    context?.goTo<FavoriteActivity>()
                }
            }
            true
        }
    }

    private fun setupViewPager(binding : FragmentMoviesBinding) {
        binding.viewpagerMovies.adapter = FragmentMovieStateAdapter(requireActivity().supportFragmentManager,
            listOf(
                getString(R.string.fragment_movies_now_playing),
                getString(R.string.fragment_movies_popular),
                getString(R.string.fragment_movies_top_rated),
                getString(R.string.fragment_movies_upcoming)
            )
        )
        binding.tablayout.setupWithViewPager(binding.viewpagerMovies)
    }

}