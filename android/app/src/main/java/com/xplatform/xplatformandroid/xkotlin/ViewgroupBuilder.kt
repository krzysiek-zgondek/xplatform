package com.xplatform.xplatformandroid.xkotlin

import android.view.View
import android.view.ViewGroup

inline fun <T : View> ViewGroup.add(builder: () -> T): T {
    val view = builder()
    addView(view)
    return view
}