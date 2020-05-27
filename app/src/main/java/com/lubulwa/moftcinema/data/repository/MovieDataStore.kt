package com.lubulwa.moftcinema.data.repository

import com.lubulwa.moftcinema.remote.model.MoftMovie
import com.lubulwa.moftcinema.remote.model.MovieResponse
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieDataStore {

    fun getTrendingMovies(page: Int): Flowable<MovieResponse>

    fun isCached(): Single<Boolean>

}