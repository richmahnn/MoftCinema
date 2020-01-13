package com.lubulwa.moftcinema.ui.di.modules

import com.lubulwa.moftcinema.data.MoftDataRepository
import com.lubulwa.moftcinema.data.executor.JobExecutor
import com.lubulwa.moftcinema.domain.MoviesRepository
import com.lubulwa.moftcinema.domain.executor.ThreadExecutor
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {

    @Binds
    abstract fun bindMovieRepository(moviesDataRepository: MoftDataRepository): MoviesRepository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

}