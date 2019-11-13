package com.akoufatzis.coolweather.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.ChangeTemperatureUnitUseCase
import com.akoufatzis.coolweather.domain.settings.Fahrenheit
import com.akoufatzis.coolweather.domain.settings.GetSettingsUseCase
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    val changeTemperatureUnitUseCase: ChangeTemperatureUnitUseCase,
    getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<SettingsViewState>()
    val viewState: LiveData<SettingsViewState>
        get() = _viewState

    init {
        val result = getSettingsUseCase()
        when (result.unit) {
            Celsius -> _viewState.value = SettingsViewState(null, TemperatureUnit.CELSIUS)
            Fahrenheit -> _viewState.value = SettingsViewState(null, TemperatureUnit.FAHRENHEIT)
        }
    }

    fun setTemperatureUnit(unit: TemperatureUnit) {
        when (unit) {
            TemperatureUnit.CELSIUS -> changeTemperatureUnitUseCase(Celsius)
            TemperatureUnit.FAHRENHEIT -> changeTemperatureUnitUseCase(Fahrenheit)
        }

        _viewState.value = SettingsViewState(null, unit)
    }
}
