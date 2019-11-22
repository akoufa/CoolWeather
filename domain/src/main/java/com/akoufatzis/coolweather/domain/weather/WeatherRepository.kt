package com.akoufatzis.coolweather.domain.weather

import com.akoufatzis.coolweather.domain.Result

interface WeatherRepository {

    suspend fun fetchWeatherData(placeName: String): Result<Weather>
}
