package com.koufatzis.coolweather.data.api.weather

import com.koufatzis.coolweather.BuildConfig
import com.koufatzis.coolweather.di.OpenWeatherMap
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

class OpenWeatherMapApi(@OpenWeatherMap private val ktorHttpClient: HttpClient) {

    suspend fun getWeather(lat: Double, lon: Double): HttpResponse {
        return ktorHttpClient.get("weather") {
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("appid", BuildConfig.OPEN_WEATHER_MAP_API_KEY)
        }
    }
}