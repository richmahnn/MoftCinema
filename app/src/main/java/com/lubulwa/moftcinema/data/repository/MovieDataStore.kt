package com.lubulwa.moftcinema.data.repository

import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieDataStore {

    fun getTrendingMovies(page: Int): Flowable<List<MoftMovie>>

    fun isCached(): Single<Boolean>

}