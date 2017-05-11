package com.akoufatzis.weatherappclean.base

import java.lang.ref.WeakReference

/**
 * Created by alexk on 05.05.17.
 */
open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    private var viewRef: WeakReference<V>? = null

    override fun attachView(view: V) {

        viewRef = WeakReference(view)
    }

    override fun detachView(retainInstance: Boolean) {

        if (viewRef != null) {
            viewRef!!.clear()
            viewRef = null
        }
    }

    val view: V?
        get() = viewRef?.get()

    val isViewAttached: Boolean
        get() = view != null
}