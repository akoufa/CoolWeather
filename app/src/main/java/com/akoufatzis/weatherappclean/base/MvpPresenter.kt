package com.akoufatzis.weatherappclean.base

/**
 * Created by alexk on 05.05.17.
 */
interface MvpPresenter<in V : MvpView> {

    fun attachView(view: V)

    fun detachView(retainInstance: Boolean)
}