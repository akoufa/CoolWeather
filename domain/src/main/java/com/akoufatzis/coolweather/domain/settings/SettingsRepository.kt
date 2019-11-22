package com.akoufatzis.coolweather.domain.settings

import com.akoufatzis.coolweather.domain.Result

interface SettingsRepository {

    fun changeTemperatureUnit(unit: TemperatureUnit)

    fun settings(): Result<Settings>

    fun temperatureUnit(): Result<TemperatureUnit>
}
