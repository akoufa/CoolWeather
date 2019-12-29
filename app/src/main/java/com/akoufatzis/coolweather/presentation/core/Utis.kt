package com.akoufatzis.coolweather.presentation.core

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


fun Context.loadJsonFromAsset(fileName: String): String? {
    return try {
        assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (e: Throwable) {
        null
    }
}

inline fun <reified T> String.toObject(): List<T>? {
    return try {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, T::class.java);
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(type)
        jsonAdapter.fromJson(this)
    } catch (e: Exception) {
        null
    }
}