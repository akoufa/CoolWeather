package com.akoufatzis.coolweather

import com.akoufatzis.coolweather.di.DaggerAppComponent
import com.akoufatzis.coolweather.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule.instance)
            .build()
    }
}
