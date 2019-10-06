package com.akoufatzis.coolweather.domain.weather

import com.akoufatzis.coolweather.domain.Result
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(cityName: String): Result<CityWeather> {
        return weatherRepository.fetchCityWeatherData(cityName)
    }
}
