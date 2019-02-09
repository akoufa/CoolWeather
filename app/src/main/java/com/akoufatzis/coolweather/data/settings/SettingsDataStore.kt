package com.akoufatzis.coolweather.data.settings

import com.akoufatzis.coolweather.domain.Result
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.Settings
import com.akoufatzis.coolweather.domain.settings.SettingsRepository
import com.akoufatzis.coolweather.domain.settings.TemperatureUnit
import javax.inject.Inject

class SettingsDataStore @Inject constructor() : SettingsRepository {
    override fun changeTemperatureUnit(unit: TemperatureUnit): Result<Unit> {
        // TODO("not implemented")
        return Success(Unit)
    }

    override fun settings(): Result<Settings> {
        return Success(Settings(Celsius))
    }
}