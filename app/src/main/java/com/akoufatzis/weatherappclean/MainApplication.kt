package com.akoufatzis.weatherappclean

import android.app.Application
import com.akoufatzis.weatherappclean.di.components.AppComponent
import com.akoufatzis.weatherappclean.di.components.DaggerAppComponent
import com.akoufatzis.weatherappclean.di.modules.AppModule
import com.akoufatzis.weatherappclean.di.modules.NetworkModule

/**
 * Created by alexk on 05.05.17.
 */
class MainApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this@MainApplication))
                .networkModule(NetworkModule(BuildConfig.OPENWEATHERMAP_URL))
                .build()
    }
}