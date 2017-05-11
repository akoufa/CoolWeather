package com.akoufatzis.weatherappclean.data.cache

/**
 * Created by alexk on 10.05.17.
 */
class MemoryItem<out T>(val item: T, private val validationPeriod: Long) {
    private val timeStamp = System.currentTimeMillis()

    val isValid: Boolean
        get() = validationPeriod == MemoryCache.VALIDATION_INFINITY
                || timeStamp + validationPeriod >= System.currentTimeMillis()

}