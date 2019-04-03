package com.akoufatzis.coolweather.presentation.weather

import com.akoufatzis.coolweather.core.Event

data class WeatherViewState(
    val progress: Event<Boolean>,
    val error: Exception?,
    val data: List<WeatherData>
)
