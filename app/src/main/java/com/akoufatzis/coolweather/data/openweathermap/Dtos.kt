package com.akoufatzis.coolweather.data.openweathermap

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CityWeatherDto(
    val id: Long,
    val name: String,
    @Json(name = "weather") val weathers: List<WeatherDto>,
    val main: MainDto,
    val wind: WindDto
)

@JsonClass(generateAdapter = true)
class MainDto(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    @Json(name = "temp_min") val tempMin: Double,
    @Json(name = "temp_max") val tempMax: Double
)

@JsonClass(generateAdapter = true)
class WeatherDto(
    val id: Long,
    val main: String?,
    val description: String?,
    val icon: String?
)

@JsonClass(generateAdapter = true)
class WindDto(val speed: Double, val deg: Double)
