@file:Suppress("ComplexMethod")

package com.akoufatzis.coolweather.presentation.weather

import android.content.Context
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.domain.weather.WeatherType
import com.akoufatzis.coolweather.presentation.settings.TemperatureUnit

/**
 * Helper method to provide the icon resource id according to the weather condition id returned
 * by the OpenWeatherMap call.
 * *
 * @return resource id for the corresponding icon. -1 if no relation is found.
 */
fun WeatherType.iconResource(): Int {
    // Based on weather code data found at:
    // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
    return when (this) {
        WeatherType.CLOUDS -> R.drawable.ic_cloudy
        WeatherType.STORM -> R.drawable.ic_storm
        WeatherType.LIGHT_RAIN -> R.drawable.ic_light_rain
        WeatherType.RAIN -> R.drawable.ic_rain
        WeatherType.SNOW -> R.drawable.ic_snow
        WeatherType.FOG -> R.drawable.ic_fog
        WeatherType.CLEAR -> R.drawable.ic_clear
        WeatherType.LIGHT_CLOUDS -> R.drawable.ic_light_clouds
        WeatherType.UNDEFINED -> -1
    }
}

/**
 * Helper method to provide the art resource id according to the weather condition id returned
 * by the OpenWeatherMap call.
 * *
 * @return resource id for the corresponding icon. -1 if no relation is found.
 */

fun WeatherType.artResource(): Int {
    // Based on weather code data found at:
    // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes

    return when (this) {
        WeatherType.CLOUDS -> R.drawable.art_clouds
        WeatherType.STORM -> R.drawable.art_storm
        WeatherType.LIGHT_RAIN -> R.drawable.art_light_rain
        WeatherType.RAIN -> R.drawable.art_rain
        WeatherType.SNOW -> R.drawable.art_snow
        WeatherType.FOG -> R.drawable.art_fog
        WeatherType.CLEAR -> R.drawable.art_fog
        WeatherType.LIGHT_CLOUDS -> R.drawable.art_light_clouds
        WeatherType.UNDEFINED -> -1 // Change this to a icon that represents unknown
    }
}

fun convertToCelsius(kelvin: Double): Double {
    @Suppress("MagicNumber")
    return kelvin - 273
}

fun convertToFahrenheit(kelvin: Double): Double {
    @Suppress("MagicNumber")
    return 1.8 * (kelvin - 273) + 32
}

// For presentation, assume the user doesn't care about tenths of a degree.
fun getDegreesRepresentation(context: Context, temperature: TemperatureData) =
    when (temperature.tempUnit) {
        TemperatureUnit.CELSIUS -> String.format(
            context.getString(R.string.format_temperature_celsius),
            temperature.degrees
        )
        TemperatureUnit.FAHRENHEIT -> String.format(
            context.getString(R.string.format_temperature_fahrenheit),
            temperature.degrees
        )
    }
