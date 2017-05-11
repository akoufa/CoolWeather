package com.akoufatzis.weatherappclean.di.components

import android.app.Application
import android.content.Context
import com.akoufatzis.weatherappclean.di.modules.AppModule
import com.akoufatzis.weatherappclean.di.modules.NetworkModule
import com.akoufatzis.weatherappclean.domain.repositories.WeatherRepository
import com.akoufatzis.weatherappclean.executors.ExecutionThread
import com.akoufatzis.weatherappclean.executors.PostExecutionThread
import dagger.Component
import javax.inject.Singleton


/**
 * Created by alexk on 05.05.17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class
))
interface AppComponent {

    fun inject(application: Application)

    val application: Application

    val applicationContext: Context

    val githubRepository: WeatherRepository

    val mainExecutionThread: PostExecutionThread

    val ioExecutionThread: ExecutionThread
}