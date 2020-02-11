package com.lubulwa.moftcinema.remote

import com.lubulwa.moftcinema.remote.model.MoftMovie
import com.lubulwa.moftcinema.data.repository.MovieRemote
import com.lubulwa.moftcinema.remote.constants.Constants
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [MovieRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class MovieRemoteImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemote {

    override fun getTrendingMovies(page: Int): Flowable<List<MoftMovie>> {
        return movieService.getTrendingMovies(page)
            .map {
                val movies = mutableListOf<MoftMovie>()
                it.moftMovies.forEach {
                    it.posterPath = Constants.TMDB_IMAGE_URL + it.posterPath
                    movies.add(it)
                }
                return@map movies
            }
    }

}