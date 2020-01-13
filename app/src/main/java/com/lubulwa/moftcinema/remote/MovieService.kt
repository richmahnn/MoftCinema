package com.lubulwa.moftcinema.remote

import com.lubulwa.moftcinema.remote.model.MovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface MovieService {

    /* Get trending movies */
    @GET("movie/now_playing")
    fun getTrendingMovies(): Flowable<MovieResponse>

}