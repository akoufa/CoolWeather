package com.akoufatzis.coolweather.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.settings.*
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    val changeTemperatureUnitUseCase: ChangeTemperatureUnitUseCase,
    getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<SettingsViewState>()
    val viewState: LiveData<SettingsViewState>
        get() = _viewState

    init {
        when (val result = getSettingsUseCase()) {
            is Success -> {
                emitViewState(settings = result.data)
            }
            is Failure -> emitErrorState(result.exception)
        }
    }

    private fun emitViewState(settings: Settings) {
        when (settings.unit) {
            is Celsius -> _viewState.value = SettingsViewState(null, TemperatureUnit.CELSIUS)
            is Fahrenheit -> _viewState.value = SettingsViewState(null, TemperatureUnit.FAHRENHEIT)
        }
    }

    private fun emitErrorState(exception: Exception) {
        _viewState.value = SettingsViewState(exception, null)
    }

    fun setTemperatureUnit(unit: TemperatureUnit) {
        when (unit) {
            TemperatureUnit.CELSIUS -> changeTemperatureUnitUseCase(Celsius)
            TemperatureUnit.FAHRENHEIT -> changeTemperatureUnitUseCase(Fahrenheit)
        }

        _viewState.value = SettingsViewState(null, unit)
    }
}
