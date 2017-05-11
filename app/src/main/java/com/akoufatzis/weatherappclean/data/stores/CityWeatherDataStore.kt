package com.akoufatzis.weatherappclean.data.stores

import com.akoufatzis.weatherappclean.BuildConfig
import com.akoufatzis.weatherappclean.data.api.RestApi
import com.akoufatzis.weatherappclean.data.cache.MemoryCache
import com.akoufatzis.weatherappclean.data.entities.CityWeatherEntity
import com.akoufatzis.weatherappclean.data.mappers.mapToCityWeather
import com.akoufatzis.weatherappclean.domain.models.CityWeather
import com.akoufatzis.weatherappclean.domain.repositories.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by alexk on 05.05.17.
 */
@Singleton
class CityWeatherDataStore @Inject constructor(val restApi: RestApi) : WeatherRepository {

    // TODO: inject this
    private val memoryCache = MemoryCache<CityWeatherEntity>()
    private val apiKey = BuildConfig.OPENWEATHERMAP_API_KEY

    override fun loadCityWeatherData(searchTerm: String): Observable<CityWeather> {

        return Observable.concat(loadFromCache(searchTerm), loadFromDb(searchTerm), loadFromNetwork(searchTerm))
                .firstElement()
                .toObservable()
                .doOnNext {
                    saveToCache(searchTerm, it)
                }
                .compose(mapToCityWeather())
    }

    private fun loadFromNetwork(searchTerm: String): Observable<CityWeatherEntity> {
        return restApi.getWeatherByCityName(searchTerm, apiKey)
                .filter { it.isSuccessful } // filter out not successful responses for the moment
                .map { it.body() }
    }

    private fun loadFromCache(searchTerm: String) = memoryCache[searchTerm]

    private fun loadFromDb(searchTerm: String) = Observable.empty<CityWeatherEntity>()

    private fun saveToCache(key: String, entity: CityWeatherEntity) {
        memoryCache.put(key, entity, MemoryCache.VALIDATION_PERIOD)
    }
}