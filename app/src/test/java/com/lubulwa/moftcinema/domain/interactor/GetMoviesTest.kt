package com.lubulwa.moftcinema.domain.interactor

import com.lubulwa.moftcinema.domain.MoviesRepository
import com.lubulwa.moftcinema.domain.executor.PostExecutionThread
import com.lubulwa.moftcinema.domain.executor.ThreadExecutor
import com.lubulwa.moftcinema.remote.model.MovieResponse
import com.lubulwa.moftcinema.utils.TestMockData
import io.reactivex.Flowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetMoviesTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var threadExecutor: ThreadExecutor

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    private lateinit var getMovies: GetMovies

    private val pageNumber = TestMockData.randomInt()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        getMovies = GetMovies(moviesRepository, threadExecutor, postExecutionThread)

    }

    @Test
    fun `buildUseCaseObservable calls repository`() {
        getMovies.buildUseCaseObservable(pageNumber)
        verify(moviesRepository).getTrendingMovies(pageNumber)
    }

    @Test
    fun `buildUseCaseObservable completes`() {
        stubMoviesRepositoryGetMovies()

        val testObserver = getMovies.buildUseCaseObservable(pageNumber).test()
        testObserver.assertComplete()
    }

    @Test
    fun `buildUseCaseObservable returns data`() {
        val response = stubMoviesRepositoryGetMovies()

        val testObserver = getMovies.buildUseCaseObservable(pageNumber).test()
        testObserver.assertValue(response.moftMovies)
    }

    private fun stubMoviesRepositoryGetMovies(): MovieResponse {
        val response = TestMockData.getTrendingMovies()
        `when`(moviesRepository.getTrendingMovies(pageNumber)).thenReturn(Flowable.just(response.moftMovies))
        return response
    }

}