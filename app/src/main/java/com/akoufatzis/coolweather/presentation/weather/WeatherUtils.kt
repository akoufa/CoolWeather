package com.akoufatzis.coolweather.presentation.weather

import android.content.Context
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.domain.weather.WeatherType

/**
 * Helper method to provide the icon resource id according to the weather condition id returned
 * by the OpenWeatherMap call.
 * *
 * @return resource id for the corresponding icon. -1 if no relation is found.
 */
fun Weather.iconResource(): Int {
    // Based on weather code data found at:
    // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
    return when (type) {
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

fun Weather.artResource(): Int {
    // Based on weather code data found at:
    // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes

    return when (type) {
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

fun Weather.celsiusDegrees(context: Context): String {
    return getDegreesRepresentation(context, convertToCelsius(this.temp))
}

fun convertToCelsius(kelvin: Double): Double {
    @Suppress("MagicNumber")
    return kelvin - 273
}

fun getDegreesRepresentation(context: Context, temperature: Double): String {
    // For presentation, assume the user doesn't care about tenths of a degree.
    return String.format(context.getString(R.string.format_temperature), temperature)
}
