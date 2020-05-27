package com.lubulwa.moftcinema.ui.di.modules

import com.lubulwa.moftcinema.domain.ItemDataSource
import com.lubulwa.moftcinema.domain.ItemDataSourceFactory
import com.lubulwa.moftcinema.data.repository.MovieRemote
import com.lubulwa.moftcinema.domain.interactor.GetMovies
import com.lubulwa.moftcinema.remote.MovieRemoteImpl
import com.lubulwa.moftcinema.remote.MovieService
import com.lubulwa.moftcinema.remote.MovieServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
abstract class RemoteModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideMovieService(): MovieService {
            return MovieServiceFactory.buildMovieService()
        }

        @Provides
        @JvmStatic
        fun provideItemDataSource(getMovies: GetMovies): ItemDataSource {
            return ItemDataSource(getMovies)
        }

        @Provides
        @JvmStatic
        fun provideItemDataSourceFactory(itemDataSource: ItemDataSource): ItemDataSourceFactory {
            return ItemDataSourceFactory(
                itemDataSource
            )
        }
    }

    @Binds
    abstract fun bindMoftRemote(movieRemoteImpl: MovieRemoteImpl): MovieRemote

}