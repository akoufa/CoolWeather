package com.akoufatzis.weatherappclean.utils

import io.reactivex.ObservableTransformer

/**
 * Created by alexk on 06.05.17.
 */
fun <UpStream, DownStream> listTransformer(transform: (UpStream) -> DownStream)
        : ObservableTransformer<List<UpStream>, List<DownStream>> {
    return ObservableTransformer {
        it.map {
            list ->
            list.map { item -> transform(item) }
        }
    }
}