package com.akoufatzis.coolweather.presentation.weather

import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.Fahrenheit
import com.akoufatzis.coolweather.domain.settings.TemperatureUnit
import com.akoufatzis.coolweather.domain.weather.CityWeather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun map(cityWeather: CityWeather, unit: TemperatureUnit): WeatherData {

        val weather = cityWeather.weather
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

        val cityName = cityWeather.city.name
        val iconRes = weather.type.iconResource()

        return WeatherData(cityName, tempData, iconRes)
    }
}
