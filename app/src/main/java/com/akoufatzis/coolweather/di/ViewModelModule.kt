package com.akoufatzis.coolweather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akoufatzis.coolweather.presentation.onboarding.OnBoardingViewModel
import com.akoufatzis.coolweather.presentation.settings.SettingsViewModel
import com.akoufatzis.coolweather.presentation.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindWeatherViewModel(weatherViewModel: WeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnBoardingViewModel::class)
    fun bindOnboardingViewModel(onBoardingViewModel: OnBoardingViewModel): ViewModel
}
