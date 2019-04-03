package com.akoufatzis.coolweather.presentation.settings

data class SettingsViewState(
    val error: Exception?,
    val data: TemperatureUnit?
)
