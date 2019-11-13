package com.akoufatzis.coolweather.domain.settings

interface SettingsRepository {

    fun changeTemperatureUnit(unit: TemperatureUnit)

    fun settings(): Settings

    fun temperatureUnit(): TemperatureUnit
}
