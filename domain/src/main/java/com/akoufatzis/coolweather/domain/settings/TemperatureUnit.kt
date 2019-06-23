package com.akoufatzis.coolweather.domain.settings

sealed class TemperatureUnit

object Celsius : TemperatureUnit()
object Fahrenheit : TemperatureUnit()
