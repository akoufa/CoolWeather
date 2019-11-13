package com.akoufatzis.coolweather.data.openweathermap

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("weather")
    suspend fun getWeatherByCityName(@Query("q") cityName: String, @Query("appid") apiKey: String): CityWeatherDto
}
