package com.lubulwa.moftcinema.remote

import com.lubulwa.moftcinema.remote.model.MoftMovie
import com.lubulwa.moftcinema.remote.model.MovieResponse
import com.lubulwa.moftcinema.utils.InstantExecutorExtension
import com.lubulwa.moftcinema.utils.TestMockData
import io.reactivex.Flowable
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MovieRemoteImplTest {

    @Mock
    private lateinit var movieService: MovieService

    private lateinit var movieRemoteImp: MovieRemoteImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        movieRemoteImp = MovieRemoteImpl(movieService)
    }

    @Test
    fun `fetch movies returns a list of movies`() {
        val response = TestMockData.getTrendingMovies()
        `when`(movieService.getTrendingMovies(ArgumentMatchers.anyInt())).thenReturn(Flowable.just(response))

        val result = movieRemoteImp.getTrendingMovies(ArgumentMatchers.anyInt()).blockingFirst()

        verify(movieService).getTrendingMovies(ArgumentMatchers.anyInt())

        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `fetch movies returns an empty list`() {
        val response = TestMockData.getTrendingMoviesEmptyList()
        `when`(movieService.getTrendingMovies(ArgumentMatchers.anyInt())).thenReturn(Flowable.just(response))

        val result = movieRemoteImp.getTrendingMovies(ArgumentMatchers.anyInt()).blockingFirst()

        verify(movieService).getTrendingMovies(ArgumentMatchers.anyInt())

        assertTrue(result.isEmpty())
    }

}