package com.akoufatzis.weatherappclean.data.stores

import com.akoufatzis.weatherappclean.BuildConfig
import com.akoufatzis.weatherappclean.data.api.RestApi
import com.akoufatzis.weatherappclean.data.cache.MemoryCache
import com.akoufatzis.weatherappclean.data.entities.CityWeatherEntity
import com.akoufatzis.weatherappclean.data.mappers.mapToCityWeather
import com.akoufatzis.weatherappclean.domain.models.*
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

    override fun loadCityWeatherData(searchTerm: String): Observable<Result<CityWeather>> {

        return Observable.concat(inflight(), loadFromCache(searchTerm), loadFromDb(searchTerm), loadFromNetwork(searchTerm))
                .take(2) // accounting for the inflight loading observable therefore take 2 instead of 1
                .doOnNext {
                    if (it.success) {
                        saveToCache(searchTerm, it.data!!)
                    }
                }
                .compose(mapToCityWeather())
                .onErrorReturn { Failure(it) }
    }

    private fun inflight(): Observable<Result<CityWeatherEntity>> {
        return Observable.just(InFlight())
    }

    private fun loadFromNetwork(searchTerm: String): Observable<Result<CityWeatherEntity>> {
        return restApi.getWeatherByCityName(searchTerm, apiKey)
                .flatMap {
                    if (!it.isSuccessful) {
                        Observable.just(Failure<CityWeatherEntity>(Throwable(it.errorBody().string())))
                    } else {
                        Observable.just(Success(it.body()))
                    }
                }
    }

    private fun loadFromCache(searchTerm: String): Observable<Result<CityWeatherEntity>> {
        return memoryCache[searchTerm].map { Success(it) }
    }

    private fun loadFromDb(searchTerm: String) = Observable.empty<Result<CityWeatherEntity>>()

    private fun saveToCache(key: String, entity: CityWeatherEntity) {
        memoryCache.put(key, entity, MemoryCache.VALIDATION_PERIOD)
    }
}