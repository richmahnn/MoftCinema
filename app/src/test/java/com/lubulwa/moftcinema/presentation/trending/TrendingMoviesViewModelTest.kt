package com.lubulwa.moftcinema.presentation.trending

import androidx.lifecycle.Observer
import com.lubulwa.moftcinema.domain.interactor.GetMovies
import com.lubulwa.moftcinema.presentation.resource.MoftResource
import com.lubulwa.moftcinema.presentation.resource.ResourceState.*
import com.lubulwa.moftcinema.remote.model.MoftMovie
import com.lubulwa.moftcinema.utils.InstantExecutorExtension
import com.lubulwa.moftcinema.utils.TestMockData
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.Mockito.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InstantExecutorExtension::class)
internal class TrendingMoviesViewModelTests {

    @Mock
    private lateinit var getMovies: GetMovies

    @Mock
    private lateinit var observer: Observer<MoftResource<List<MoftMovie>>>

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<MoftResource<List<MoftMovie>>>

    private lateinit var viewModel: TrendingMoviesViewModel

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = TrendingMoviesViewModel(getMovies)
        viewModel.observeTrendingMovies().observeForever(observer)
    }

    @Nested
    inner class Loading {

        @Test
        fun `fetch movies triggers loading state`() {
            stubGetMoviesSuccess()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState) = allValues
                assertThat(loadState.status, `is`(LOADING))
            }
        }

        @Test
        fun `fetch movies contains no data when loading`() {
            stubGetMoviesSuccess()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState) = allValues
                assert(loadState.data == null)
            }
        }

        @Test
        fun `fetch movies contains no message when loading`() {
            stubGetMoviesSuccess()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState) = allValues
                assert(loadState.message == null)
            }
        }

    }

    @Nested
    inner class Error {

        @Test
        fun `fetch movies returns error`() {
            stubGetMoviesError()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState, errorState) = allValues
                assertThat(errorState.status, `is`(ERROR))
            }
        }

        @Test
        fun `fetch movies fails and returns message`() {
            stubGetMoviesError()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState, errorState) = allValues
                assert(errorState.message!!.isNotEmpty())
            }
        }

        @Test
        fun `fetch movies fails and returns no data`() {
            stubGetMoviesError()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState, errorState) = allValues
                assertThat(errorState.status, `is`(ERROR))
                assert(errorState.data == null)
            }
        }
    }

    @Nested
    inner class Success {
        @Test
        fun `fetch movies triggers success state`() {
            stubGetMoviesSuccess()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState, successState) = allValues
                assertThat(successState.status, `is`(SUCCESS))
            }
        }

        @Test
        fun `fetch movies returns data on success`() {
            stubGetMoviesSuccess()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState, successState) = allValues
                assert(successState.data!!.size == TestMockData.getTrendingMovies().moftMovies.size)
            }
        }

        @Test
        fun `fetch movies returns no message on success`() {
            stubGetMoviesSuccess()

            argumentCaptor.run {
                verify(observer, times(2)).onChanged(capture())
                val (loadState, successState) = allValues
                assert(successState.message == null)
            }
        }
    }

    private fun stubGetMoviesSuccess() {
        `when`(getMovies.execute(ArgumentMatchers.anyInt())).thenReturn(Flowable.just(TestMockData.getTrendingMovies().moftMovies))
        viewModel.fetchMovies()
    }

    private fun stubGetMoviesError() {
        `when`(getMovies.execute(ArgumentMatchers.anyInt())).thenReturn(Flowable.error(Throwable("Error occurred!")))
        viewModel.fetchMovies()
    }

}