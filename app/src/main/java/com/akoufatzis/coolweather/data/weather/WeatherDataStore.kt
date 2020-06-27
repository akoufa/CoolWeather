package com.akoufatzis.coolweather.data.weather

import com.akoufatzis.coolweather.BuildConfig
import com.akoufatzis.coolweather.data.openweathermap.OpenWeatherMapApi
import com.akoufatzis.coolweather.data.weather.mappers.toWeather
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Result
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.weather.Weather
import com.akoufatzis.coolweather.domain.weather.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

const val API_KEY = BuildConfig.OPENWEATHERMAP_API_KEY

@Singleton
class WeatherDataStore @Inject constructor(private val api: OpenWeatherMapApi) :
    WeatherRepository {
    override suspend fun fetchWeatherData(placeName: String): Result<Weather> {

        return try {
            val weatherDto = api.getWeatherByCityName(
                placeName,
                API_KEY
            )
            Success(weatherDto.toWeather())
        } catch (@Suppress("TooGenericExceptionCaught") exception: Exception) {
            Failure(exception)
        }
    }
}
