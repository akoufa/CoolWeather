package com.akoufatzis.coolweather.domain.settings

import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Settings {
        return settingsRepository.settings()
    }
}
