package com.akoufatzis.coolweather.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.akoufatzis.coolweather.presentation.onboarding.OnBoardingFragment
import com.akoufatzis.coolweather.presentation.onboarding.OnboardingActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnboardingActivityModule {
    @Binds
    fun bindAppCompatActivity(onboardingActivity: OnboardingActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeOnboardingFragment(): OnBoardingFragment
}
