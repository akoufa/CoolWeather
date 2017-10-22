package com.akoufatzis.weatherappclean.domain.usecases

import com.akoufatzis.weatherappclean.domain.models.CityWeather
import com.akoufatzis.weatherappclean.domain.models.Result
import com.akoufatzis.weatherappclean.domain.repositories.WeatherRepository
import com.akoufatzis.weatherappclean.domain.usecases.GetCityWeatherUseCase.Params
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by alexk on 05.05.17.
 */
class GetCityWeatherUseCase @Inject constructor(private val repository: WeatherRepository)
    : ObservableUseCase<Params, Result<CityWeather>> {

    class Params(val cityNames: Observable<CharSequence>)

    override fun execute(params: Params): Observable<Result<CityWeather>> {

        // apply business logic
        val cityNamesObservable = params
                .cityNames
                .map{it.toString()}
                .debounce(400L, TimeUnit.MILLISECONDS)
                .map { it.trim() }
                .filter { it.length > 2 }

        return repository.
                loadCityWeatherData(cityNames = cityNamesObservable)
    }
}