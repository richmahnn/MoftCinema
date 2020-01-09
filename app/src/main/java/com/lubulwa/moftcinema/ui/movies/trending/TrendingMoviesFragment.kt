package com.lubulwa.moftcinema.ui.movies.trending

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lubulwa.moftcinema.R

class TrendingMoviesFragment : Fragment() {

    companion object {
        fun newInstance() =
            TrendingMoviesFragment()
    }

    private lateinit var viewModel: TrendingMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trending_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrendingMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
