package com.akoufatzis.weatherappclean.data.cache

import io.reactivex.Observable


/**
 * Created by alexk on 10.05.17.
 */

// TODO: Change this to an LRU cache
class MemoryCache<T> {

    private val memoryCache = HashMap<String, MemoryItem<T>>()

    fun put(key: String, value: T, validationPeriod: Long) {

        memoryCache.put(key, MemoryItem(value, validationPeriod))
    }

    operator fun get(key: String): Observable<T> {

        val memoryItem = memoryCache[key]

        return if (memoryItem != null && memoryItem.isValid) Observable.just(memoryItem.item) else Observable.empty<T>()
    }

    fun getAsValue(key: String) = memoryCache[key]?.item

    fun clear() {
        memoryCache.clear()
    }

    companion object {
        // 5 min
        val VALIDATION_PERIOD = (1000 * 60 * 5).toLong()

        //Special value for infinity
        val VALIDATION_INFINITY: Long = -1
    }
}