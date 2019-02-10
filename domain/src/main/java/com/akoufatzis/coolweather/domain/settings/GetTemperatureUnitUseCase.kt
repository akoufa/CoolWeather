package com.akoufatzis.coolweather.domain.settings

import com.akoufatzis.coolweather.domain.Result
import javax.inject.Inject

class GetTemperatureUnitUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Result<TemperatureUnit> {
        return settingsRepository.temperatureUnit()
    }
}