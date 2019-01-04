package com.akoufatzis.coolweather.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.akoufatzis.coolweather.MainActivity
import com.akoufatzis.coolweather.presentation.weather.WeatherFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @Binds
    fun bindAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeWeatherFragment(): WeatherFragment
}
