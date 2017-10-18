package com.akoufatzis.weatherappclean.search.viewmodel

import android.arch.lifecycle.ViewModel
import com.akoufatzis.weatherappclean.domain.models.Result
import com.akoufatzis.weatherappclean.domain.usecases.GetCityWeatherUseCase
import com.akoufatzis.weatherappclean.domain.usecases.GetCityWeatherUseCase.Params
import com.akoufatzis.weatherappclean.executors.PostExecutionThread
import com.akoufatzis.weatherappclean.search.model.CityWeatherModel
import com.akoufatzis.weatherappclean.search.model.mapToCityWeatherModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by alexk on 07.05.17.
 */
class SearchViewModel @Inject constructor(private val useCase: GetCityWeatherUseCase, private val mainThread: PostExecutionThread) : ViewModel() {

    fun search(textChanges: Observable<CharSequence>): Observable<Result<CityWeatherModel>> {
        return textChanges
                .filter { it.length > 2 }
                .map {
                    Params(it.toString())
                }
                .compose(useCase.execute())
                .compose(mapToCityWeatherModel())
                .observeOn(mainThread.scheduler)
    }
}