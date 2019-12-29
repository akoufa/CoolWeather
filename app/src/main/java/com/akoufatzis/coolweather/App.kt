package com.akoufatzis.coolweather

import com.akoufatzis.coolweather.di.DatabaseModule
import com.akoufatzis.coolweather.di.PlacesModule
import com.akoufatzis.coolweather.di.DaggerAppComponent
import com.akoufatzis.coolweather.di.OpenWeatherMapModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder()
            .application(this)
            .openWeatherMapModule(OpenWeatherMapModule.instance)
            .databaseModule(DatabaseModule)
            .placesModule(PlacesModule.instance)
            .build()
    }
}
