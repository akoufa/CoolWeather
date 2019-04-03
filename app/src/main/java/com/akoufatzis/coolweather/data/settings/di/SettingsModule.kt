package com.akoufatzis.coolweather.data.settings.di

import com.akoufatzis.coolweather.data.settings.SettingsDataStore
import com.akoufatzis.coolweather.domain.settings.SettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SettingsModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideSettingsModule(
        settingsRepository: SettingsDataStore
    ): SettingsRepository =
        settingsRepository
}
