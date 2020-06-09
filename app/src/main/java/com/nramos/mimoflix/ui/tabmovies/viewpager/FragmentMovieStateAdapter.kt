package com.nramos.mimoflix.ui.tabmovies.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nramos.mimoflix.ui.tabmovies.viewpager.fragments.PopularMoviesFragment

class FragmentMovieStateAdapter(
    fm : FragmentManager,
    private val titleList : List<String>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> { PopularMoviesFragment() }
        1 -> { PopularMoviesFragment() }
        2 -> { PopularMoviesFragment() }
        else -> { PopularMoviesFragment() }
    }

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

}