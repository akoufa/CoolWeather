package com.akoufatzis.coolweather.data.entities

import com.akoufatzis.coolweather.domain.weather.Main
import com.akoufatzis.coolweather.domain.weather.Weather
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
    @Suppress("ConstructorParameterNaming") val temp_min: Double,
    @Suppress("ConstructorParameterNaming") val temp_max: Double
) {

    fun toMainModel(): Main {
        return Main(temp, pressure, humidity, temp_min, temp_max)
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
