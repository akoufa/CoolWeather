package com.akoufatzis.weatherappclean.executors

import io.reactivex.Scheduler

/**
 * Created by alexk on 10.05.17.
 */
interface ExecutionThread {
    val scheduler: Scheduler
}