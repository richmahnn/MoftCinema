package com.lubulwa.moftcinema.data

import com.lubulwa.moftcinema.data.source.MovieDataStoreFactory
import com.lubulwa.moftcinema.domain.MoviesRepository
import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Provides an implementation of the [MoviesRepository] interface (in the domain layer)
 * for communicating to and from data sources
 */
class MoftDataRepository @Inject constructor(private val factory: MovieDataStoreFactory) : MoviesRepository  {

    override fun getTrendingMovies(): Flowable<List<MoftMovie>> {
        return factory.retrieveDataStore().getTrendingMovies()
    }

}