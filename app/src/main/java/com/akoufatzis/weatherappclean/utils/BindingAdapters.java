package com.akoufatzis.weatherappclean.utils;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by alexk on 11.05.17.
 */

public class BindingAdapters {

    @BindingAdapter("android:drawableEnd")
    public static void drawableEnd(TextView view, int resourceId) {
        view.setCompoundDrawablesWithIntrinsicBounds(0, 0, resourceId, 0);
    }
}
