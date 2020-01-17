package com.lubulwa.moftcinema.presentation.trending

import com.lubulwa.moftcinema.domain.interactor.GetMovies
import com.lubulwa.moftcinema.presentation.resource.MoftResource
import com.lubulwa.moftcinema.presentation.resource.ResourceState.LOADING
import com.lubulwa.moftcinema.presentation.resource.ResourceState.SUCCESS
import com.lubulwa.moftcinema.remote.model.MoftMovie
import com.lubulwa.moftcinema.utils.InstantExecutorExtension
import com.lubulwa.moftcinema.utils.LiveDataTestUtil
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import java.lang.RuntimeException

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InstantExecutorExtension::class)
internal class TrendingMoviesViewModelTest {

    @Mock
    private lateinit var getMovies: GetMovies

    @Captor
    private lateinit var moftResource: ArgumentCaptor<MoftResource<List<MoftMovie>>>

    private lateinit var viewModel: TrendingMoviesViewModel

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = TrendingMoviesViewModel(getMovies)
    }

    @Nested
    inner class ResourceState {

        @Test
        fun `fetch movies triggers loading state`() {
            val result = viewModel.observeTrendingMovies()
            assertThat(result.value!!.status, `is`(LOADING))
        }

        @Test
        fun `fetch movies contains no data when loading`() {
            val result = viewModel.observeTrendingMovies()
            assert(result.value!!.data == null)
        }

        @Test
        fun `fetch movies contains no message when loading`() {
            val result = viewModel.observeTrendingMovies()
            assert(result.value!!.message == null)
        }

        @Test
        fun `fetch movies returns error`() {
//            viewModel.observeTrendingMovies()
//            val result = viewModel.observeTrendingMovies()
//            `when`(getMovies.execute(ArgumentMatchers.any()))
//                .thenReturn(Flowable.error(Throwable()))
        }

//        @Test
//        fun `fetch movies triggers success state`() {
//            val liveDataTestUtil = LiveDataTestUtil<MoftResource<List<MoftMovie>>>()
//
//            viewModel.fetchMovies()
//            val observedData = liveDataTestUtil.getValue(viewModel.observeTrendingMovies())
//            //verify(observer, atLeast(1)).onChanged(moftResource.capture())
//
//            assertThat(observedData.status, `is`(SUCCESS))
//        }

    }

}