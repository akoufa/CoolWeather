package com.akoufatzis.coolweather.di

import android.app.Application
import com.akoufatzis.coolweather.App
import com.akoufatzis.coolweather.di.activity.ActivityBuilder
import com.akoufatzis.coolweather.openweathermap.di.OpenWeatherMapModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        OpenWeatherMapModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun openWeatherMapModule(module: OpenWeatherMapModule): Builder
        fun build(): AppComponent
    }

    override fun inject(app: App)
}
