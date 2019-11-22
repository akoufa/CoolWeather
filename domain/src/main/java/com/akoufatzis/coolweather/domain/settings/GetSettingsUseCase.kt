package com.akoufatzis.coolweather.domain.settings

import com.akoufatzis.coolweather.domain.Result
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Result<Settings> {
        return settingsRepository.settings()
    }
}
