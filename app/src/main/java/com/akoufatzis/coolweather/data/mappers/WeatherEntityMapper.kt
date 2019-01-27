@file:Suppress("ForbiddenComment", "MagicNumber", "ComplexMethod", "WildcardImport")
package com.akoufatzis.coolweather.data.mappers

import com.akoufatzis.coolweather.data.entities.CityWeatherEntity
import com.akoufatzis.coolweather.domain.weather.* // ktlint-disable no-wildcard-imports

// TODO: Don't depended on external service ids
fun CityWeatherEntity.toCityWeather(): CityWeather {
    val weather = weathers.first()

    val type = mapToWeatherType(weather.id)
    val weatherDomainModel = Weather(weather.id, weather.main, weather.description, type)
    val temperature = Temperature(main.temp, main.pressure, main.humidity, main.tempMin, main.tempMax)
    val city = City(id, name)
    return CityWeather(weatherDomainModel, temperature, city)
}

fun mapToWeatherType(weatherId: Long): WeatherType {
    // Based on weather code data found at:
    // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
    return when (weatherId) {
        in 200..232 -> WeatherType.STORM
        in 300..321 -> WeatherType.LIGHT_RAIN
        in 500..504 -> WeatherType.RAIN
        511L -> WeatherType.SNOW
        in 520..531 -> WeatherType.RAIN
        in 600..622 -> WeatherType.SNOW
        in 701..761 -> WeatherType.FOG
        761L, 781L -> WeatherType.STORM
        800L -> WeatherType.CLEAR
        801L -> WeatherType.LIGHT_CLOUDS
        in 802..804 -> WeatherType.CLOUDS
        // Change this to a icon that represents unknown
        else -> WeatherType.UNDEFINED
    }
}
