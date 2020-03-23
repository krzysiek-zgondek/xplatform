package com.xplatform.xplatformandroid.xkotlin.layouts

import android.view.View
import com.xplatform.xkotlin.ui.layouts.XClickableLayout

class AndroidXClickableLayout(val view: View) :
    XClickableLayout {

    override fun setOnClick(action: (() -> Unit)?) {
        view.setOnClickListener { action?.invoke() }
    }
}