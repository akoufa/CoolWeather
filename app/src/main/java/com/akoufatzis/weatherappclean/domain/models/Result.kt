package com.akoufatzis.weatherappclean.domain.models

/**
 * Created by alexk on 16.05.17.
 */
class Result<out Data>(val success: Boolean, val loading: Boolean, val error: Throwable?, val data: Data?) {

    companion object {

        fun <Data> success(data: Data) = Result(true, false, null, data)
        fun <Data> error(error: Throwable) = Result<Data>(false, false, error, null)
        fun <Data> inflight() = Result<Data>(false, true, null, null)
    }
}