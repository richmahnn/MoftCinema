package com.lubulwa.moftcinema.data.repository

import com.lubulwa.moftcinema.remote.model.MoftMovie
import com.lubulwa.moftcinema.remote.model.MovieResponse
import io.reactivex.Flowable

/**
 * Interface defining methods for the caching of Movies. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface MovieRemote {

    /**
     * Retrieve a list of trending Movies, from the cache
     */
    fun getTrendingMovies(page: Int): Flowable<MovieResponse>

}