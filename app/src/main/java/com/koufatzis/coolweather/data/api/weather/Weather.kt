package com.koufatzis.coolweather.data.api.weather

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
class OpenWeatherMapResponse(
    val id: Long,
    val name: String,
    @JsonNames("weather") val weathers: List<Weather>,
    val main: Main,
)

@Serializable
class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

@Serializable
class Main(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    @JsonNames("temp_min") val tempMin: Double,
    @JsonNames("temp_max") val tempMax: Double,
)