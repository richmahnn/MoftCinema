package com.lubulwa.moftcinema.ui.movies.trending

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.lubulwa.moftcinema.presentation.trending.TrendingMoviesViewModel
import com.lubulwa.moftcinema.ui.di.modules.FragmentViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class TrendingMoviesModule {

    @Binds
    abstract fun bindsTrendingMoviesFragment(trendingMoviesFragment: TrendingMoviesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(value = TrendingMoviesViewModel::class)
    abstract fun bindTrendingMoviesViewModel(trendingMoviesVM: TrendingMoviesViewModel): ViewModel


//    @Module
//    companion object {
//
//        @Provides
//        fun provideTrendingMoviesAdapter() = TrendingMoviesAdapter()
//
//    }

}