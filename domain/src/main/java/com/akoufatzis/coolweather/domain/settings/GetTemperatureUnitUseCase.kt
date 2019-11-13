package com.akoufatzis.coolweather.domain.settings

import javax.inject.Inject

class GetTemperatureUnitUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): TemperatureUnit {
        return settingsRepository.temperatureUnit()
    }
}
