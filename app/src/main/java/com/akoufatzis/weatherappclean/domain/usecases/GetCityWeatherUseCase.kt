package com.akoufatzis.weatherappclean.domain.usecases

import com.akoufatzis.weatherappclean.domain.models.CityWeather
import com.akoufatzis.weatherappclean.domain.models.Result
import com.akoufatzis.weatherappclean.domain.repositories.WeatherRepository
import com.akoufatzis.weatherappclean.domain.usecases.GetCityWeatherUseCase.Params
import io.reactivex.ObservableTransformer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by alexk on 05.05.17.
 */
class GetCityWeatherUseCase @Inject constructor(private val repository: WeatherRepository)
    : TransformerUseCase<Params, Result<CityWeather>> {

    class Params(val searchTerm: String)

    override fun execute(): ObservableTransformer<Params, Result<CityWeather>> {
        return ObservableTransformer {
            it.debounce(300, TimeUnit.MILLISECONDS)
                    .map { it.searchTerm.trim() }
                    .switchMap {
                        repository.loadCityWeatherData(it)
                    }
        }

    }
}