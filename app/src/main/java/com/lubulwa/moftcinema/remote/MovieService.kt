package com.lubulwa.moftcinema.remote

import com.lubulwa.moftcinema.remote.model.MovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface MovieService {

    /* Get now playing movies */
    @GET("movie/now_playing")
    fun getTrendingMovies(): Flowable<MovieResponse>

    /* Get trending movies */
    @GET("movie/top_rated")
    fun getTopRatedMovies(): Flowable<MovieResponse>

}