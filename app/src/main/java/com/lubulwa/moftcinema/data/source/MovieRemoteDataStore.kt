package com.lubulwa.moftcinema.data.source

import com.lubulwa.moftcinema.data.repository.MovieDataStore
import com.lubulwa.moftcinema.data.repository.MovieRemote
import com.lubulwa.moftcinema.remote.model.MoftMovie
import com.lubulwa.moftcinema.remote.model.MovieResponse
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [MovieDataStore] interface to provide a means of communicating
 * with the remote data source
 */
class MovieRemoteDataStore @Inject constructor(private val movieRemote: MovieRemote) : MovieDataStore {

    /**
     * Retrieve a list of [MoftMovie] instances from the API
     */
    override fun getTrendingMovies(page: Int): Flowable<MovieResponse> {
        return movieRemote.getTrendingMovies(page)
    }

    override fun isCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}