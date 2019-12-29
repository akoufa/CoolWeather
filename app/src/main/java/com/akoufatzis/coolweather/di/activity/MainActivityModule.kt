package com.akoufatzis.coolweather.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.akoufatzis.coolweather.presentation.main.MainActivity
import com.akoufatzis.coolweather.presentation.main.MainFragment
import com.akoufatzis.coolweather.presentation.places.PlacesFragment
import com.akoufatzis.coolweather.presentation.settings.SettingsFragment
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

    @ContributesAndroidInjector
    fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    fun contributePlacesFragment(): PlacesFragment

    @ContributesAndroidInjector
    fun contributeMainFragment(): MainFragment
}
