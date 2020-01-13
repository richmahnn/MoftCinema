package com.lubulwa.moftcinema.ui.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lubulwa.moftcinema.presentation.ViewModelProviderFactory
import com.lubulwa.moftcinema.ui.movies.HomeModule
import com.lubulwa.moftcinema.ui.movies.trending.TrendingMoviesModule
import dagger.MapKey
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Annotation class to identify Activity view models by classname.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ActivityViewModelKey(val value: KClass<out ViewModel>)


/**
 * Annotation class to identify Fragment view models by classname.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@MustBeDocumented
annotation class FragmentViewModelKey(val value: KClass<out ViewModel>)

/**
 * Module that provides all dependencies from the presentation package/layer.
 */
@Module(includes = [ HomeModule::class, TrendingMoviesModule::class ])
abstract class PresentationModule {

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
            return ViewModelProviderFactory(
                providerMap
            )
        }
    }

}