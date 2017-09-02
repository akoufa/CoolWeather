package com.akoufatzis.weatherappclean.di.modules

import com.akoufatzis.weatherappclean.di.scopes.PerActivity
import com.akoufatzis.weatherappclean.search.mvp.view.MvpSearchActivity
import com.akoufatzis.weatherappclean.search.mvvm.view.MvvmSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by alexk on 27.08.17.
 */
@Module
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(SearchModule::class))
    abstract fun contributeMvpSearchActivity(): MvpSearchActivity
    @ContributesAndroidInjector
    abstract fun contributeMvvmSearchActivity(): MvvmSearchActivity
}