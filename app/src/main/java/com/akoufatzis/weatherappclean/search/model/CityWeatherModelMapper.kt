package com.akoufatzis.weatherappclean.search.model

import com.akoufatzis.weatherappclean.domain.models.CityWeather
import com.akoufatzis.weatherappclean.domain.models.Result
import io.reactivex.ObservableTransformer

/**
 * Created by alexk on 05.05.17.
 */
fun mapToCityWeatherModel() = ObservableTransformer<Result<CityWeather>, Result<CityWeatherModel>> {
    it.map { cityWeatherResult ->
        if (cityWeatherResult.success) {
            val data = cityWeatherResult.data!!
            val model = CityWeatherModel(data.weathers.first(), data.main.temp, data.city)
            Result.success(model)
        } else if (cityWeatherResult.loading) {
            Result.inflight()
        } else {
            Result.error(cityWeatherResult.error!!)
        }
    }
}