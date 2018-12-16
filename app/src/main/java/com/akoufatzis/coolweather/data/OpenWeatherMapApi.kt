package com.akoufatzis.coolweather.data

import com.akoufatzis.coolweather.data.entities.CityWeatherEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("public/getcurrencies")
    fun getWeatherByCityName(@Query("q") cityName: String, @Query("appid") apiKey: String): Deferred<CityWeatherEntity>
}
