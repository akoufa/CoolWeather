package com.akoufatzis.coolweather.domain.weather

data class CityWeather(val weather: Weather, val temperature: Temperature, val city: City)

data class City(val id: Long, val name: String)

data class Temperature(
    val value: Double,
    val pressure: Double,
    val humidity: Double,
    val min: Double,
    val max: Double
)

data class Weather(val id: Long, val header: String?, val description: String?, val type: WeatherType)

enum class WeatherType {
    STORM,
    LIGHT_RAIN,
    RAIN,
    SNOW,
    FOG,
    CLEAR,
    LIGHT_CLOUDS,
    CLOUDS,
    UNDEFINED
}
