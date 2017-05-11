package com.akoufatzis.weatherappclean.domain.usecases

import io.reactivex.Observable
import io.reactivex.ObservableTransformer


/**
 * Created by alexk on 05.05.17.
 */
interface UseCase<T, in Params> {
    fun execute(params: Params): Observable<T>
}

interface TransformerUseCase<Upstream, Downstream> {
    fun execute(): ObservableTransformer<Upstream, Downstream>
}