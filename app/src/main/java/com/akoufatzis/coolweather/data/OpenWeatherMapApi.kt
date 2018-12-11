package com.akoufatzis.coolweather.data

import com.akoufatzis.coolweather.data.entities.CityWeatherEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface OpenWeatherMapApi {

    @GET("public/getcurrencies")
    fun getWeather(): Deferred<CityWeatherEntity>
}