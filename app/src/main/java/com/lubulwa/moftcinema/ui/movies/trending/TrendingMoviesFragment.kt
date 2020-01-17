package com.lubulwa.moftcinema.ui.movies.trending

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.lubulwa.moftcinema.presentation.resource.ResourceState

import com.lubulwa.moftcinema.R
import com.lubulwa.moftcinema.presentation.ViewModelProviderFactory
import com.lubulwa.moftcinema.presentation.trending.TrendingMoviesViewModel
import com.lubulwa.moftcinema.remote.model.MoftMovie
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.trending_movies_fragment.*
import javax.inject.Inject

class TrendingMoviesFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var trendingMoviesAdapter: TrendingMoviesAdapter

    private lateinit var viewModel: TrendingMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trending_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, providerFactory).get(TrendingMoviesViewModel::class.java)

        setupTrendingMoviesList()
        setupViewListeners()
    }

    private fun setupTrendingMoviesList() {
        rv_trending_movies.layoutManager = GridLayoutManager(activity, 2)
        rv_trending_movies.adapter = trendingMoviesAdapter
    }

    private fun setupViewListeners() {

    }

    private fun handleDataState(state: ResourceState, data: List<MoftMovie>?, message: String?) {
        when(state) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccessState(data)
            ResourceState.ERROR -> setupScreenForErrorState(message)
        }
    }

    private fun setupScreenForLoadingState() {

    }

    private fun setupScreenForSuccessState(data: List<MoftMovie>?) {
        if (data != null && data.isNotEmpty()) {
            updateListView(data)
        }
    }

    private fun setupScreenForErrorState(message: String?) {

    }

    private fun updateListView(movies: List<MoftMovie>) {
        trendingMoviesAdapter.movies = movies
        trendingMoviesAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        viewModel.observeTrendingMovies().observe(this, Observer {
            if (it != null) this.handleDataState(it.status, it.data, it.message)
        })
        viewModel.fetchMovies()
    }

}
