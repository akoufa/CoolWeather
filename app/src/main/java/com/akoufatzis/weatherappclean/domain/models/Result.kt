package com.akoufatzis.weatherappclean.domain.models

/**
 * Created by alexk on 16.05.17.
 */
sealed class Result<out Data>(val success: Boolean, val loading: Boolean, val error: Throwable?, val data: Data?)

class InFlight<out Data> : Result<Data>(false, true, null, null)
class Success<out Data>(data: Data) : Result<Data>(true, false, null, data)
class Failure<out Data>(error: Throwable) : Result<Data>(false, false, error, null)