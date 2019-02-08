package com.akoufatzis.coolweather.domain.weather

data class CityWeather(val weather: Weather, val city: City)

data class City(val name: String)

data class Temperature(
    val value: Double,
    val min: Double,
    val max: Double
)

inline class Pressure(val value: Double)

inline class Humidity(val value: Double)

data class Weather(
    val description: String?,
    val type: WeatherType,
    val temperature: Temperature,
    val pressure: Pressure,
    val humidity: Humidity,
    val wind: Wind
)

data class Wind(
    val speed: Double,
    val direction: Double
)

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
