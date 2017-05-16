package com.akoufatzis.weatherappclean.data.mappers

import com.akoufatzis.weatherappclean.data.entities.CityWeatherEntity
import com.akoufatzis.weatherappclean.domain.models.City
import com.akoufatzis.weatherappclean.domain.models.CityWeather
import com.akoufatzis.weatherappclean.domain.models.Result
import io.reactivex.ObservableTransformer

/**
 * Created by alexk on 05.05.17.
 */
fun mapToCityWeather() = ObservableTransformer <Result<CityWeatherEntity>, Result<CityWeather>> {
    it.map { entityResult ->

        if (entityResult.success) {
            val data = entityResult.data!!
            val weatherModelList = data.weathers.map { w -> w.toWeatherModel() }
            val mainModel = data.main.toMainModel()
            val city = City(data.id, data.name)
            val cw = CityWeather(weatherModelList, mainModel, city)
            Result.success(cw)
        } else if (entityResult.loading) {
            Result.inflight<CityWeather>()
        } else {
            Result.error<CityWeather>(entityResult.error!!)
        }
    }
}