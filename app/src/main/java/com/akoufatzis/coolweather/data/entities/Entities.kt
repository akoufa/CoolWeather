package com.akoufatzis.coolweather.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CityWeatherEntity(
    val id: Long,
    val name: String,
    val weather: List<WeatherEntity>,
    val main: MainEntity
)

class MainEntity(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    @Json(name = "temp_min") val tempMin: Double,
    @Json(name = "temp_max") val tempMax: Double
)

class WeatherEntity(
    val id: Long,
    val main: String?,
    val description: String?,
    val icon: String?
)
