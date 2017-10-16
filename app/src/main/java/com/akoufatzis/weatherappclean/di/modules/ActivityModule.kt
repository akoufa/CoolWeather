package com.akoufatzis.weatherappclean.di.modules

import com.akoufatzis.weatherappclean.search.view.MvvmSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by alexk on 27.08.17.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMvvmSearchActivity(): MvvmSearchActivity
}