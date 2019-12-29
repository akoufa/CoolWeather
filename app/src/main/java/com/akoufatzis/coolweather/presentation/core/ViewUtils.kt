package com.akoufatzis.coolweather.presentation.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate


@ExperimentalCoroutinesApi
fun TextView.onTextChange() = callbackFlow {
    val listener = addTextChangedListener { text ->
        if (text != null) {
            offer(text.toString())
        }
    }
    awaitClose { removeTextChangedListener(listener) }
}.conflate()

fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
