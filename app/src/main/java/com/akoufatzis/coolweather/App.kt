package com.akoufatzis.coolweather

import com.akoufatzis.coolweather.data.database.di.DatabaseModule
import com.akoufatzis.coolweather.data.places.di.PlacesModule
import com.akoufatzis.coolweather.di.DaggerAppComponent
import com.akoufatzis.coolweather.openweathermap.di.OpenWeatherMapModule
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
