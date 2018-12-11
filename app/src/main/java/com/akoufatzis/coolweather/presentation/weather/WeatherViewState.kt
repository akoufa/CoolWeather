package com.akoufatzis.coolweather.presentation.weather

import com.akoufatzis.coolweather.core.Event
import com.akoufatzis.coolweather.domain.weather.CityWeather

data class WeatherViewState(
    val progress: Event<Boolean>,
    val error: Exception?,
    val data: CityWeather?
)
