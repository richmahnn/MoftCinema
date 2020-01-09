package com.lubulwa.moftcinema.ui.movies

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lubulwa.moftcinema.R
import com.lubulwa.moftcinema.ui.movies.trending.TrendingMoviesFragment

class MoftPagerAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mContext: Context = context

    // This determines the fragment for each tab
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TrendingMoviesFragment()
            }
            else -> {
                TrendingMoviesFragment()
            }
        }
    }

    // This determines the title for each tab
    override fun getPageTitle(position: Int): CharSequence? { // Generate title based on item position
        return when (position) {
            0 -> mContext.getString(R.string.trending_movies)
            1 -> mContext.getString(R.string.popular_movies)
            2 -> mContext.getString(R.string.coming_soon_movies)
            else -> null
        }
    }

    // This determines the number of tabs
    override fun getCount(): Int {
        return 3
    }

}