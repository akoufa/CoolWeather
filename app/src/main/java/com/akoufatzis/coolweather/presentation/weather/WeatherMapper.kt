package com.akoufatzis.coolweather.presentation.weather

import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.Fahrenheit
import com.akoufatzis.coolweather.domain.settings.TemperatureUnit
import com.akoufatzis.coolweather.domain.weather.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun map(placeName: String, weather: Weather, unit: TemperatureUnit): WeatherData {

        val temperature = weather.temperature

        val tempData = when (unit) {
            Celsius -> TemperatureData(
                convertToCelsius(temperature.value),
                com.akoufatzis.coolweather.presentation.settings.TemperatureUnit.CELSIUS
            )
            Fahrenheit -> TemperatureData(
                convertToFahrenheit(temperature.value),
                com.akoufatzis.coolweather.presentation.settings.TemperatureUnit.FAHRENHEIT
            )
        }

        val iconRes = weather.type.iconResource()

        return WeatherData(placeName, tempData, iconRes)
    }
}
