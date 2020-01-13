package com.lubulwa.moftcinema.ui.di.modules

import com.lubulwa.moftcinema.domain.executor.PostExecutionThread
import com.lubulwa.moftcinema.ui.UiThread
import com.lubulwa.moftcinema.ui.di.scopes.HomeScope
import com.lubulwa.moftcinema.ui.movies.HomeActivity
import com.lubulwa.moftcinema.ui.movies.HomeModule
import com.lubulwa.moftcinema.ui.movies.trending.TrendingMoviesFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module that provides all dependencies from the mobile-ui package/layer and injects all activities.
 */
@Module
abstract class UiModule {

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @HomeScope
    @ContributesAndroidInjector
    abstract fun contributeTrendingFragment(): TrendingMoviesFragment

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}