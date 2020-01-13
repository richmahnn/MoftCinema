package com.lubulwa.moftcinema.ui.movies

import android.app.Application
import androidx.fragment.app.FragmentManager
import com.lubulwa.moftcinema.ui.di.scopes.HomeScope
import com.lubulwa.moftcinema.ui.movies.trending.TrendingMoviesModule
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideFragmentManager(activity: HomeActivity): FragmentManager {
            return activity.supportFragmentManager
        }

        @Provides
        @JvmStatic
        fun provideMoftPagerAdapter(application: Application, fm: FragmentManager): MoftPagerAdapter {
            return MoftPagerAdapter(application.applicationContext, fm)
        }

    }

}