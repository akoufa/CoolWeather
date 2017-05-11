package com.akoufatzis.weatherappclean.di.modules

import android.app.Application
import android.content.Context
import com.akoufatzis.weatherappclean.executors.ExecutionThread
import com.akoufatzis.weatherappclean.executors.IoExecutionThread
import com.akoufatzis.weatherappclean.executors.MainExecutionThread
import com.akoufatzis.weatherappclean.executors.PostExecutionThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by alexk on 05.05.17.
 */
@Module
class AppModule(internal var application: Application) {

    @Provides
    @Singleton
    fun providesPostExecutionThread(mainExecutionThread: MainExecutionThread): PostExecutionThread = mainExecutionThread

    @Provides
    @Singleton
    fun providesExecutionThread(ioExecutionThread: IoExecutionThread): ExecutionThread = ioExecutionThread

    @Provides
    @Singleton
    fun providesApplication(): Application {

        return application
    }

    @Provides
    @Singleton
    fun providesApplicationContext(): Context {

        return application.applicationContext
    }
}