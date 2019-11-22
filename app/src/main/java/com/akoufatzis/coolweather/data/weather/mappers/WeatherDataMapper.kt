@file:Suppress("ForbiddenComment", "MagicNumber", "ComplexMethod", "WildcardImport")

package com.akoufatzis.coolweather.data.weather.mappers

import com.akoufatzis.coolweather.data.openweathermap.CityWeatherDto
import com.akoufatzis.coolweather.domain.weather.*

fun CityWeatherDto.toWeather(): Weather {
    val weather = weathers.first()

    val type = mapToWeatherType(weather.id)
    val temperature = Temperature(main.temp)
    val minTemperature = Temperature(main.tempMin)
    val maxTemperature = Temperature(main.tempMax)
    val humidity = Humidity(main.humidity)
    val pressure = Pressure(main.pressure)
    val windDomainModel = Wind(wind.speed, wind.deg)

    return Weather(
        description = weather.description,
        humidity = humidity,
        pressure = pressure,
        temperature = temperature,
        minTemperature = minTemperature,
        maxTemperature = maxTemperature,
        wind = windDomainModel,
        type = type
    )
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
