package com.akoufatzis.coolweather.domain.weather

import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Result
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.place.PlaceRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val placeRepository: PlaceRepository
) {

    suspend operator fun invoke(): Result<CityWeather> {
        return when(val placeResult = placeRepository.fetchPlace()){
            is Success -> weatherRepository.fetchCityWeatherData(placeResult.data.name)
            is Failure -> Failure(Exception("Weather could not be determined"))
        }
    }
}
