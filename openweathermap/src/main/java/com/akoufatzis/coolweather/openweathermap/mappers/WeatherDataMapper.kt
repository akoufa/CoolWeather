@file:Suppress("ForbiddenComment", "MagicNumber", "ComplexMethod", "WildcardImport")

package com.akoufatzis.coolweather.openweathermap.mappers

import com.akoufatzis.coolweather.domain.weather.* // ktlint-disable no-wildcard-imports
import com.akoufatzis.coolweather.openweathermap.data.CityWeatherDto

// TODO: Don't depended on external service ids
fun CityWeatherDto.toCityWeather(): CityWeather {
    val weather = weathers.first()

    val type = mapToWeatherType(weather.id)
    val temperature = Temperature(main.temp, main.tempMin, main.tempMax)
    val humidity = Humidity(main.humidity)
    val pressure = Pressure(main.pressure)
    val city = City(name)
    val windDomainModel = Wind(wind.speed, wind.deg)

    val weatherDomainModel = Weather(
        description = weather.description,
        humidity = humidity,
        pressure = pressure,
        temperature = temperature,
        wind = windDomainModel,
        type = type
    )
    return CityWeather(weatherDomainModel, city)
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
