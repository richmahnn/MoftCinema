package com.lubulwa.moftcinema.domain.interactor

import com.lubulwa.moftcinema.domain.MoviesRepository
import com.lubulwa.moftcinema.domain.executor.PostExecutionThread
import com.lubulwa.moftcinema.domain.executor.ThreadExecutor
import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Movie] instances from the [MoviesRepository]
 */
class GetMovies @Inject constructor(
    private val moviesRepository: MoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<MoftMovie>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<MoftMovie>> {
        return moviesRepository.getTrendingMovies()
    }

}