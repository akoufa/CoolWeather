package com.akoufatzis.weatherappclean.di.components

import com.akoufatzis.weatherappclean.di.modules.SearchModule
import com.akoufatzis.weatherappclean.di.scopes.PerActivity
import com.akoufatzis.weatherappclean.search.mvp.presenter.SearchPresenter
import com.akoufatzis.weatherappclean.search.mvp.view.MvpSearchActivity
import com.akoufatzis.weatherappclean.search.mvvm.view.MvvmSearchActivity
import com.akoufatzis.weatherappclean.search.mvvm.viewmodel.SearchViewModel
import dagger.Component

/**
 * Created by alexk on 05.05.17.
 */
@PerActivity
@Component(modules = arrayOf(
        SearchModule::class
), dependencies = arrayOf(AppComponent::class))
interface SearchActivityComponent {
    /**
     * For MVP
     */
    fun inject(activity: MvpSearchActivity)

    val presenter: SearchPresenter

    /**
     * For MVVM
     */
    fun inject(activity: MvvmSearchActivity)

    val viewModel: SearchViewModel


}