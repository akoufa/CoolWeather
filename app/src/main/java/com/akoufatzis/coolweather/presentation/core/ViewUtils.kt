package com.akoufatzis.coolweather.presentation.core

import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
    setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}

fun EditText.onEnterAction(onClicked: (view: TextView) -> Unit) {
    setOnEditorActionListener { view, actionId, event ->
        var handled = false

        if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == ACTION_DOWN) {
            onClicked(view)
            handled = true
        } else if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onClicked(view)
            handled = true
        }
        handled
    }
}
