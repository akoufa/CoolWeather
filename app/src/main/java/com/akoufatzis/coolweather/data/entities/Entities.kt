package com.akoufatzis.coolweather.data.entities

import com.akoufatzis.coolweather.domain.weather.Main
import com.akoufatzis.coolweather.domain.weather.Weather
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
    @Json(name= "temp_min")val tempMin: Double,
    @Json(name="temp_max") val tempMax: Double
) {

    fun toMainModel(): Main {
        return Main(temp, pressure, humidity, tempMin, tempMax)
    }
}

class WeatherEntity(
    val id: Long,
    val main: String?,
    val description: String?,
    val icon: String?
) {

    fun toWeatherModel(): Weather {
        return Weather(id, main, description, icon)
    }
}
