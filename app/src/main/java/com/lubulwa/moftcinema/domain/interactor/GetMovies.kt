package com.lubulwa.moftcinema.domain.interactor

import com.lubulwa.moftcinema.domain.MoviesRepository
import com.lubulwa.moftcinema.domain.executor.PostExecutionThread
import com.lubulwa.moftcinema.domain.executor.ThreadExecutor
import com.lubulwa.moftcinema.remote.model.MovieResponse
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Movie] instances from the [MoviesRepository]
 */
class GetMovies @Inject constructor(
    private val moviesRepository: MoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<MovieResponse, Int>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Int?): Flowable<MovieResponse> {
        return moviesRepository.getTrendingMovies(params ?: 1) // 1 is the default page number
    }
}