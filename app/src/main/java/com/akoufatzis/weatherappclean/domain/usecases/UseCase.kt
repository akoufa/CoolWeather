package com.akoufatzis.weatherappclean.domain.usecases

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single


/**
 * Created by alexk on 05.05.17.
 */

interface SingleUseCase<in Params, Result> {
    fun execute(params: Params): Single<Result>
}

interface ObservableUseCase<in Params, Result> {
    fun execute(params: Params): Observable<Result>
}

interface TransformerUseCase<Upstream, Downstream> {
    fun execute(): ObservableTransformer<Upstream, Downstream>
}