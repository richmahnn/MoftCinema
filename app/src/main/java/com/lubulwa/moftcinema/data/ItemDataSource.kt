package com.lubulwa.moftcinema.data

import androidx.paging.PageKeyedDataSource
import com.lubulwa.moftcinema.remote.MovieService
import com.lubulwa.moftcinema.remote.model.MoftMovie
import timber.log.Timber
import javax.inject.Inject

class ItemDataSource @Inject constructor(private val movieService: MovieService) : PageKeyedDataSource<Int, MoftMovie>() {

    private val firstPage = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MoftMovie>) {
        Timber.e("loadInitial page number: $firstPage")
        val result = movieService.getTrendingMovies(firstPage)
            .blockingFirst()
        callback.onResult(result.moftMovies, null, firstPage + 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MoftMovie>) {
        Timber.e("loadAfter page number: ${params.key}")
        val result = movieService.getTrendingMovies(params.key)
            .blockingFirst()

        val key = if (result.page < result.totalPages) params.key + 1 else 1

        callback.onResult(result.moftMovies, key)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MoftMovie>) {
        Timber.e("loadBefore page number: ${params.key}")
        val result = movieService.getTrendingMovies(params.key)
            .blockingFirst()

        val key = if (params.key > 1) params.key - 1 else 1

        callback.onResult(result.moftMovies, key)
    }


}