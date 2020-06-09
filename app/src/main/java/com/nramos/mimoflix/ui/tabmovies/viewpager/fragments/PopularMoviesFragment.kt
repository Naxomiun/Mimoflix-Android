package com.nramos.mimoflix.ui.tabmovies.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nramos.mimoflix.databinding.FragmentPopularMoviesBinding
import com.nramos.mimoflix.ui.tabmovies.MoviesFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PopularMoviesFragment : Fragment(){

    private val viewModel : MoviesFragmentViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentPopularMoviesBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

}