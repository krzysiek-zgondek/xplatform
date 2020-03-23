package com.xplatform.xplatformandroid.xkotlin.layouts

import android.widget.TextView
import com.xplatform.xkotlin.ui.layouts.XTextLayout

class AndroidXTextLayout(val view: TextView):
    XTextLayout {
    override fun setText(text: String?) {
        view.text = text
    }

    override fun getText(): String? {
        return view.text?.toString()
    }
}