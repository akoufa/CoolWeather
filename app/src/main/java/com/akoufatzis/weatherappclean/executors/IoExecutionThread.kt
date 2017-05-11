package com.akoufatzis.weatherappclean.executors

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by alexk on 10.05.17.
 */
@Singleton
class IoExecutionThread @Inject constructor() : ExecutionThread {
    override val scheduler: Scheduler = Schedulers.io()
}