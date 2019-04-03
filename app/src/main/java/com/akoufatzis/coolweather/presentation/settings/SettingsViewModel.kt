package com.akoufatzis.coolweather.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.ChangeTemperatureUnitUseCase
import com.akoufatzis.coolweather.domain.settings.Fahrenheit
import com.akoufatzis.coolweather.domain.settings.GetSettingsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SettingsViewModel @Inject constructor(
    val changeTemperatureUnitUseCase: ChangeTemperatureUnitUseCase,
    getSettingsUseCase: GetSettingsUseCase
) :
    ViewModel(), CoroutineScope {

    private val job = Job()
    @Suppress("ForbiddenComment")
    // TODO: Inject the coroutineContext
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val _viewState = MutableLiveData<SettingsViewState>()
    val viewState: LiveData<SettingsViewState>
        get() = _viewState

    init {
        val result = getSettingsUseCase()
        if (result is Success) {
            val unit = result.data.unit
            when (unit) {
                Celsius -> _viewState.value = SettingsViewState(null, TemperatureUnit.CELSIUS)
                Fahrenheit -> _viewState.value = SettingsViewState(null, TemperatureUnit.FAHRENHEIT)
            }
        }
    }

    fun setTemperatureUnit(unit: TemperatureUnit) {
        val result = when (unit) {
            TemperatureUnit.CELSIUS -> changeTemperatureUnitUseCase(Celsius)
            TemperatureUnit.FAHRENHEIT -> changeTemperatureUnitUseCase(Fahrenheit)
        }

        when (result) {
            is Success -> _viewState.value = SettingsViewState(null, unit)
            is Failure -> _viewState.value = SettingsViewState(result.exception, null)
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}
