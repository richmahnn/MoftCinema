package com.lubulwa.moftcinema.data

import com.lubulwa.moftcinema.data.repository.MovieDataStore
import com.lubulwa.moftcinema.data.source.MovieDataStoreFactory
import com.lubulwa.moftcinema.utils.InstantExecutorExtension
import com.lubulwa.moftcinema.utils.TestMockData
import io.reactivex.Flowable
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InstantExecutorExtension::class)
internal class MoftDataRepositoryTest {

    @Mock
    private lateinit var factory: MovieDataStoreFactory

    @Mock
    private lateinit var movieDataStore: MovieDataStore

    @Mock
    private lateinit var repo: MoftDataRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        repo = MoftDataRepository(factory)
    }

    @Test
    fun `fetch trending movies completes`() {
        val response = TestMockData.getTrendingMovies()
        `when`(factory.retrieveDataStore()).thenReturn(movieDataStore)
        `when`(movieDataStore.getTrendingMovies()).thenReturn(Flowable.just(response.moftMovies))

        val testObserver = repo.getTrendingMovies().test()

        testObserver.assertComplete()
    }

    @Test
    fun `fetch trending movies returns data`() {
        val response = TestMockData.getTrendingMovies()
        `when`(factory.retrieveDataStore()).thenReturn(movieDataStore)
        `when`(movieDataStore.getTrendingMovies()).thenReturn(Flowable.just(response.moftMovies))

        val testObserver = repo.getTrendingMovies().test()
        testObserver.assertValue(response.moftMovies)
    }

}