package com.akoufatzis.coolweather.domain

sealed class Result<out T : Any>

data class Success<out T : Any>(val data: T) : Result<T>()
data class Failure(val exception: Exception) : Result<Nothing>()

fun <In : Any, Out : Any> Success<In>.map(mapperFunc: (input: In) -> Out): Success<Out> {
    return Success(mapperFunc(this.data))
}
