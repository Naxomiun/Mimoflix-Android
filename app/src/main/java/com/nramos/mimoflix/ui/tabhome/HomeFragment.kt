package com.nramos.mimoflix.ui.tabhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nramos.mimoflix.databinding.FragmentHomeBinding
import com.nramos.mimoflix.extension.goTo
import com.nramos.mimoflix.extension.observe
import com.nramos.mimoflix.model.localgenre.LocalGenre
import com.nramos.mimoflix.ui.companylist.MoviesCompanyActivity
import com.nramos.mimoflix.ui.genrelist.MoviesGenreActivity
import com.nramos.mimoflix.ui.moviedetail.MovieDetailActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class HomeFragment : Fragment() {

    private val viewModel : HomeFragmentViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentHomeBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvents()
    }

    private fun setEvents() {
        with(viewModel){
            observe(movieActionEvent) {
                it.getContentIfNotHandled()?.let { movieId ->
                    context?.goTo<MovieDetailActivity>(Pair("movie", movieId))
                }
            }
            observe(companyActionEvent) {
                it.getContentIfNotHandled()?.let { company ->
                    context?.goTo<MoviesCompanyActivity>(Pair("company", company))
                }
            }
            observe(genreActionEvent) {
                it.getContentIfNotHandled()?.let { genre ->
                    context?.goTo<MoviesGenreActivity>(Pair("genre", genre))
                }
            }
        }
    }

}