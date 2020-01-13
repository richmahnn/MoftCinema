package com.lubulwa.moftcinema.ui.di

import android.app.Application
import com.lubulwa.moftcinema.Moft
import com.lubulwa.moftcinema.ui.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    UiModule::class,
    PresentationModule::class,
    DomainModule::class,
    DataModule::class,
    RemoteModule::class
])
interface AppComponent : AndroidInjector<Moft> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}