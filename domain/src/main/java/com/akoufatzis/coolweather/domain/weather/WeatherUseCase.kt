package com.akoufatzis.coolweather.domain.weather

import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(cityName: String): CityWeather {
        return weatherRepository.fetchCityWeatherData(cityName)
    }
}
