package com.lubulwa.moftcinema.domain

import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.Flowable

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface MoviesRepository {

    fun getTrendingMovies(page: Int): Flowable<List<MoftMovie>>

}