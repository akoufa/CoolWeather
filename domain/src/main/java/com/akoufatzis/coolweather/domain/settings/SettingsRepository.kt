package com.akoufatzis.coolweather.domain.settings

import com.akoufatzis.coolweather.domain.Result

interface SettingsRepository {

    fun changeTemperatureUnit(unit: TemperatureUnit): Result<Unit>

    fun settings(): Result<Settings>
}