package com.akoufatzis.weatherappclean.utils

import android.content.Context
import com.akoufatzis.weatherappclean.R
import com.akoufatzis.weatherappclean.search.model.CityWeatherModel

/**
 * Created by alexk on 11.05.17.
 */
class WeatherUtils {
    /**
     * Helper method to provide the icon resource id according to the weather condition id returned
     * by the OpenWeatherMap call.

     * @param weatherId from OpenWeatherMap API response
     * *
     * @return resource id for the corresponding icon. -1 if no relation is found.
     */
    fun getIconResourceForWeatherCondition(weatherId: Int): Int {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId in 200..232) {
            return R.drawable.ic_storm
        } else if (weatherId in 300..321) {
            return R.drawable.ic_light_rain
        } else if (weatherId in 500..504) {
            return R.drawable.ic_rain
        } else if (weatherId == 511) {
            return R.drawable.ic_snow
        } else if (weatherId in 520..531) {
            return R.drawable.ic_rain
        } else if (weatherId in 600..622) {
            return R.drawable.ic_snow
        } else if (weatherId in 701..761) {
            return R.drawable.ic_fog
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.ic_storm
        } else if (weatherId == 800) {
            return R.drawable.ic_clear
        } else if (weatherId == 801) {
            return R.drawable.ic_light_clouds
        } else if (weatherId in 802..804) {
            return R.drawable.ic_cloudy
        }
        return -1
    }

    /**
     * Helper method to provide the art resource id according to the weather condition id returned
     * by the OpenWeatherMap call.

     * @param weatherId from OpenWeatherMap API response
     * *
     * @return resource id for the corresponding icon. -1 if no relation is found.
     */

    fun artResource(cityWeatherModel: CityWeatherModel): Int {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        val weatherId = cityWeatherModel.weather.id.toInt()
        if (weatherId in 200..232) {
            return R.drawable.art_storm
        } else if (weatherId in 300..321) {
            return R.drawable.art_light_rain
        } else if (weatherId in 500..504) {
            return R.drawable.art_rain
        } else if (weatherId == 511) {
            return R.drawable.art_snow
        } else if (weatherId in 520..531) {
            return R.drawable.art_rain
        } else if (weatherId in 600..622) {
            return R.drawable.art_snow
        } else if (weatherId in 701..761) {
            return R.drawable.art_fog
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.art_storm
        } else if (weatherId == 800) {
            return R.drawable.art_clear
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds
        } else if (weatherId in 802..804) {
            return R.drawable.art_clouds
        }
        // Change this to a icon that represents unknown
        return -1
    }

    fun celsiusDegrees(context: Context, temperature: Double): String {
        return getDegreesRepresentation(context, convertToCelsius(temperature))
    }

    fun convertToCelsius(kelvin: Double): Double {
        return kelvin - 273
    }

    fun getDegreesRepresentation(context: Context, temperature: Double): String {
        // For presentation, assume the user doesn't care about tenths of a degree.
        return String.format(context.getString(R.string.format_temperature), temperature)
    }
}

