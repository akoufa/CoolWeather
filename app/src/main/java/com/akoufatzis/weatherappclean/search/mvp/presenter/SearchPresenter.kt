package com.akoufatzis.weatherappclean.search.mvp.presenter

import com.akoufatzis.weatherappclean.base.BasePresenter
import com.akoufatzis.weatherappclean.di.scopes.PerActivity
import com.akoufatzis.weatherappclean.domain.usecases.GetCityWeatherUseCase
import com.akoufatzis.weatherappclean.domain.usecases.GetCityWeatherUseCase.Params
import com.akoufatzis.weatherappclean.executors.PostExecutionThread
import com.akoufatzis.weatherappclean.search.model.mapToCityWeatherModel
import com.akoufatzis.weatherappclean.search.mvp.SearchContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by alexk on 05.05.17.
 */
@PerActivity
class SearchPresenter @Inject constructor(val useCase: GetCityWeatherUseCase,
                                          val mainThread: PostExecutionThread)
    : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun attachView(view: SearchContract.View) {
        super.attachView(view)
    }

    override fun search(textChanges: Observable<CharSequence>) {

        val disposable = textChanges
                .filter { it.length > 2 }
                .map {
                    Params(it.toString())
                }
                .compose(useCase.execute())
                .compose(mapToCityWeatherModel())
                .observeOn(mainThread.scheduler)
                .subscribe({
                    result ->
                    view?.showLoading(result.loading)
                    if (result.success) {
                        view?.showCityWeather(result.data!!)
                    } else if (result.error != null) {
                        view?.showError()
                    }

                }, {
                    view?.showError()
                })

        compositeDisposable.add(disposable)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}