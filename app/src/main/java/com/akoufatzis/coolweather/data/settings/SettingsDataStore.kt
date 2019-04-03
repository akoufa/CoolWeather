@file:Suppress("WildcardImport")
package com.akoufatzis.coolweather.data.settings

import android.content.SharedPreferences
import androidx.core.content.edit
import com.akoufatzis.coolweather.domain.Result
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.settings.* // ktlint-disable no-wildcard-imports
import javax.inject.Inject

class SettingsDataStore @Inject constructor(private val sharedPreferences: SharedPreferences) : SettingsRepository {

    private val celsiusKey = "CELSIUS_KEY"

    override fun temperatureUnit(): Result<TemperatureUnit> {
        val tempUnit = createTempUnit()
        return Success(tempUnit)
    }

    override fun changeTemperatureUnit(unit: TemperatureUnit): Result<Unit> {
        val isCelsius = unit == Celsius
        sharedPreferences.edit {
            putBoolean(celsiusKey, isCelsius)
        }
        return Success(Unit)
    }

    override fun settings(): Result<Settings> {
        val tempUnit = createTempUnit()
        return Success(Settings(tempUnit))
    }

    private fun createTempUnit(): TemperatureUnit {
        val useCelsius = sharedPreferences.getBoolean(celsiusKey, true)
        return if (useCelsius) Celsius else Fahrenheit
    }
}
