package com.akoufatzis.coolweather.di.activity

import com.akoufatzis.coolweather.MainActivity
import com.akoufatzis.coolweather.di.scopes.ActivityScope
import com.akoufatzis.coolweather.presentation.onboarding.OnboardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [OnboardingActivityModule::class])
    fun contributeOnboardingActivity(): OnboardingActivity
}
