package com.akoufatzis.coolweather.presentation.weather

data class CityWeather(val weather: Weather, val city: City)

data class Weather(val id: Long, val temp: Double, val icon: String?)

inline class City(val name: String)

fun createCityWeather(domainModel: com.akoufatzis.coolweather.domain.weather.CityWeather): CityWeather {
    val weather = domainModel.weathers.first()
    val value = domainModel.temperature.value
    val cityName = domainModel.city.name

    val weatherModel = Weather(weather.id, value, weather.icon)
    return CityWeather(weatherModel, City(cityName))
}