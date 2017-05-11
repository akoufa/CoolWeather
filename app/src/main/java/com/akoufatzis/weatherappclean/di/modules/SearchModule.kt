package com.akoufatzis.weatherappclean.di.modules

import com.akoufatzis.weatherappclean.di.scopes.PerActivity
import com.akoufatzis.weatherappclean.domain.usecases.GetCityWeatherUseCase
import com.akoufatzis.weatherappclean.executors.PostExecutionThread
import com.akoufatzis.weatherappclean.search.mvp.SearchContract
import com.akoufatzis.weatherappclean.search.mvp.presenter.SearchPresenter
import com.akoufatzis.weatherappclean.search.mvvm.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by alexk on 05.05.17.
 */
@Module
class SearchModule {
    /**
     * For MVP
     */
    @PerActivity
    @Provides
    fun providesPresenter(githubReposUseCase: GetCityWeatherUseCase, executionThread: PostExecutionThread): SearchContract.Presenter {
        return SearchPresenter(githubReposUseCase, executionThread)
    }

    /**
     * For MVVM
     */
    @PerActivity
    @Provides
    fun providesViewModel(githubReposUseCase: GetCityWeatherUseCase): SearchViewModel {
        return SearchViewModel(githubReposUseCase)
    }
}