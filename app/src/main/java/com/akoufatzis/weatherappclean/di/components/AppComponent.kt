package com.akoufatzis.weatherappclean.di.components

import android.app.Application
import android.content.Context
import com.akoufatzis.weatherappclean.MainApplication
import com.akoufatzis.weatherappclean.di.modules.ActivityModule
import com.akoufatzis.weatherappclean.di.modules.AppModule
import com.akoufatzis.weatherappclean.domain.repositories.WeatherRepository
import com.akoufatzis.weatherappclean.executors.ExecutionThread
import com.akoufatzis.weatherappclean.executors.PostExecutionThread
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by alexk on 05.05.17.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: MainApplication)

    val applicationContext: Context

    val githubRepository: WeatherRepository

    val mainExecutionThread: PostExecutionThread

    val ioExecutionThread: ExecutionThread
}