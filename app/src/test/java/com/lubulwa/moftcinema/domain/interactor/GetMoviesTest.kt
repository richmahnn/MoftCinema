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
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        getMovies = GetMovies(moviesRepository, threadExecutor, postExecutionThread)

    }

    @Test
    fun `buildUseCaseObservable calls repository`() {
        getMovies.buildUseCaseObservable(null)
        verify(moviesRepository).getTrendingMovies()
    }

    @Test
    fun `buildUseCaseObservable completes`() {
        stubMoviesRepositoryGetMovies()

        val testObserver = getMovies.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun `buildUseCaseObservable returns data`() {
        val response = stubMoviesRepositoryGetMovies()

        val testObserver = getMovies.buildUseCaseObservable(null).test()
        testObserver.assertValue(response.moftMovies)
    }

    private fun stubMoviesRepositoryGetMovies(): MovieResponse {
        val response = TestMockData.getTrendingMovies()
        `when`(moviesRepository.getTrendingMovies()).thenReturn(Flowable.just(response.moftMovies))
        return response
    }

}