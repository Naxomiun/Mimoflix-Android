package com.nramos.mimoflix.ui.tabmovies.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nramos.mimoflix.ui.tabmovies.viewpager.fragments.NowPlayingMoviesFragment
import com.nramos.mimoflix.ui.tabmovies.viewpager.fragments.PopularMoviesFragment
import com.nramos.mimoflix.ui.tabmovies.viewpager.fragments.TopRatedMoviesFragment
import com.nramos.mimoflix.ui.tabmovies.viewpager.fragments.UpcomingMoviesFragment

class FragmentMovieStateAdapter(
    fm : FragmentManager,
    private val titleList : List<String>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> { NowPlayingMoviesFragment() }
        1 -> { PopularMoviesFragment() }
        2 -> { TopRatedMoviesFragment() }
        else -> { UpcomingMoviesFragment() }
    }

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

}