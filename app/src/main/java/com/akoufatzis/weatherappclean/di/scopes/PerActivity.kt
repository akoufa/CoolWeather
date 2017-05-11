package com.akoufatzis.weatherappclean.di.scopes

import javax.inject.Scope

/**
 * Created by alexk on 05.05.17.
 */
/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity