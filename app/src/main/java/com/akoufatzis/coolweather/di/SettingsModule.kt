package com.akoufatzis.coolweather.di

import com.akoufatzis.coolweather.data.settings.SettingsDataStore
import com.akoufatzis.coolweather.domain.settings.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object SettingsModule {

    @Singleton
    @Provides
    fun provideSettingsModule(
        settingsRepository: SettingsDataStore
    ): SettingsRepository =
        settingsRepository
}
