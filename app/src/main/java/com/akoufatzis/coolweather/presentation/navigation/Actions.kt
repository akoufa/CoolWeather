package com.akoufatzis.coolweather.presentation.navigation

import android.content.Context
import android.content.Intent

/*
 This enables us to split the app in multiple modules, every of them contains a activity that can be started via this object according
 to https://jeroenmols.com/blog/2019/04/02/modularizationexample/
 */

object Actions {
    fun openMainIntent(context: Context): Intent = internalIntent(
        context,
        "action.main.open"
    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}
