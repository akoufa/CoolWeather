package com.akoufatzis.coolweather.domain.weather

data class CityWeather(val weathers: List<Weather>, val main: Main, val city: City)

data class City(val id: Long, val name: String)

data class Main(
    val temp: Double, val pressure: Double, val humidity: Double,
    val tempMin: Double, val tempMax: Double
)

data class Weather(val id: Long, val main: String?, val description: String?, val icon: String?)