package com.akoufatzis.coolweather.domain.settings

import com.akoufatzis.coolweather.domain.Result
import javax.inject.Inject

class ChangeTemperatureUnitUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(unit: TemperatureUnit): Result<Unit> {
        return settingsRepository.changeTemperatureUnit(unit)
    }
}
