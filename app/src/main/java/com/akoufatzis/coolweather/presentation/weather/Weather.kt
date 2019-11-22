package com.akoufatzis.coolweather.presentation.weather

import com.akoufatzis.coolweather.presentation.settings.TemperatureUnit

data class WeatherData(val place: String, val tempData: TemperatureData, val iconRes: Int)

data class TemperatureData(val degrees: Double, val tempUnit: TemperatureUnit)
