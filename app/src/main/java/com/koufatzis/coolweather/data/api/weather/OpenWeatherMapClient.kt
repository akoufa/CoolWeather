package com.koufatzis.coolweather.data.api.weather

import io.ktor.client.call.body
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenWeatherMapClient @Inject constructor(
    private val openWeatherMapApi: OpenWeatherMapApi,
) {

    suspend fun getWeather(lat: Double, lon: Double): Result<OpenWeatherMapResponse> {
        return runCatching {
            openWeatherMapApi.getWeather(lat = lat, lon = lon).body()
        }
    }
}