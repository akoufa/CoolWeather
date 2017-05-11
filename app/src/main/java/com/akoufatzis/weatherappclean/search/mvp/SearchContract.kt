package com.akoufatzis.weatherappclean.search.mvp

import com.akoufatzis.weatherappclean.base.MvpPresenter
import com.akoufatzis.weatherappclean.base.MvpView
import com.akoufatzis.weatherappclean.search.model.CityWeatherModel
import io.reactivex.Observable

/**
 * Created by alexk on 05.05.17.
 */
interface SearchContract {

    interface View : MvpView {

        fun showCityWeather(cityWeather: CityWeatherModel)
        fun showLoading(show: Boolean)
        fun showError()
    }

    interface Presenter : MvpPresenter<View> {

        fun search(textChanges: Observable<CharSequence>)
    }
}