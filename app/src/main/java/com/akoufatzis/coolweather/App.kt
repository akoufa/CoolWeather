package com.akoufatzis.coolweather

import com.akoufatzis.coolweather.BuildConfig.PLACES_API_KEY
import com.akoufatzis.coolweather.data.database.di.DatabaseModule
import com.akoufatzis.coolweather.data.places.di.PlacesModule
import com.akoufatzis.coolweather.di.DaggerAppComponent
import com.akoufatzis.coolweather.di.OpenWeatherMapModule
import com.google.android.libraries.places.api.Places
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext, PLACES_API_KEY)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder()
            .application(this)
            .openWeatherMapModule(OpenWeatherMapModule.instance)
            .databaseModule(DatabaseModule)
            .placesModule(PlacesModule.instance)
            .build()
    }
}
