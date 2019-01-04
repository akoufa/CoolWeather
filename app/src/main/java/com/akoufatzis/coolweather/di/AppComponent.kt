package com.akoufatzis.coolweather.di

import android.app.Application
import com.akoufatzis.coolweather.App
import com.akoufatzis.coolweather.di.activity.ActivityBuilder
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
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(module: NetworkModule): Builder
        fun build(): AppComponent
    }

    override fun inject(app: App)
}
