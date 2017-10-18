package com.akoufatzis.weatherappclean.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.akoufatzis.weatherappclean.di.utils.ViewModelKey
import com.akoufatzis.weatherappclean.factories.WeatherViewModelFactory
import com.akoufatzis.weatherappclean.search.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by alexk on 27.08.17.
 */

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindUserViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: WeatherViewModelFactory): ViewModelProvider.Factory
}