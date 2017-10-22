package com.akoufatzis.weatherappclean.search.model

import com.akoufatzis.weatherappclean.domain.models.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * Created by alexk on 05.05.17.
 */
fun toCityWeatherModel() = ObservableTransformer<Result<CityWeather>, Result<CityWeatherModel>> {
    it.map { result ->
        when (result) {
            is Success -> {
                val data = result.data!!
                val model = CityWeatherModel(data.weathers.first(), data.main.temp, data.city)
                Success(model)
            }
            is InFlight -> InFlight()
            is Failure -> Failure<CityWeatherModel>(result.error!!)
        }
    }
}

fun Observable<Result<CityWeather>>.mapToCityWeatherModel() = this.compose(toCityWeatherModel())