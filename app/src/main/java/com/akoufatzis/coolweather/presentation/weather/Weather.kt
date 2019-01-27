package com.akoufatzis.coolweather.presentation.weather

import com.akoufatzis.coolweather.domain.weather.WeatherType

data class CityWeather(val weather: Weather, val city: City)

data class Weather(val id: Long, val temp: Double, val type: WeatherType)

inline class City(val name: String)

fun createCityWeather(domainModel: com.akoufatzis.coolweather.domain.weather.CityWeather): CityWeather {
    val weather = domainModel.weather
    val value = domainModel.temperature.value
    val cityName = domainModel.city.name

    val weatherModel = Weather(weather.id, value, weather.type)
    return CityWeather(weatherModel, City(cityName))
}
