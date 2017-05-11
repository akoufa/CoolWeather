package com.akoufatzis.weatherappclean.domain.models

import com.google.gson.annotations.SerializedName

/**
 * Created by alexk on 11.05.17.
 */
data class CityWeather(val weathers: List<Weather>, val main: Main, val city: City)

data class City(val id: Long, val name: String)

data class Main(val temp: Double, val pressure: Double, val humidity: Double,
                @SerializedName("temp_min") val tempMin: Double, @SerializedName("temp_max") val tempMax: Double)

data class Weather(val id: Long, val main: String?, val description: String?, val icon: String?)