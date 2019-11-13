package com.akoufatzis.coolweather.domain.settings

import javax.inject.Inject

class ChangeTemperatureUnitUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(unit: TemperatureUnit) {
        return settingsRepository.changeTemperatureUnit(unit)
    }
}
