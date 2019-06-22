package com.akoufatzis.coolweather.presentation.onboarding

import android.os.Bundle
import com.akoufatzis.coolweather.R
import dagger.android.support.DaggerAppCompatActivity

class OnboardingActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }
}
