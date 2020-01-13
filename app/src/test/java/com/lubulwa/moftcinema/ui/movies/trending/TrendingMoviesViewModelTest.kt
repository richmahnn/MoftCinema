package com.lubulwa.moftcinema.ui.movies.trending

import com.lubulwa.moftcinema.data.MoftDataRepository
import com.lubulwa.moftcinema.presentation.trending.TrendingMoviesViewModel
import com.lubulwa.moftcinema.utils.InstantExecutorExtension
import com.lubulwa.moftcinema.utils.TestMockData
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InstantExecutorExtension::class)
internal class TrendingMoviesViewModelTest {

    @Mock
    private lateinit var dataRepository: MoftDataRepository

    private lateinit var viewModel: TrendingMoviesViewModel

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel =
            TrendingMoviesViewModel(
                dataRepository
            )
    }

    @Nested
    inner class FetchTrending {

        @Test
        fun `should return list of movies`() {
            `when`(dataRepository.getTrendingMovies()).thenReturn(TestMockData.getTrendingMovies())
            val results = viewModel.fetchMovies()

            verify(viewModel.fetchMovies())
        }

    }
}