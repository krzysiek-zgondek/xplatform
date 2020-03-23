package com.xplatform.xplatformandroid.xkotlin.layouts

import android.view.ViewGroup
import android.widget.TextView
import com.xplatform.xplatformandroid.xkotlin.add
import com.xplatform.xkotlin.ui.layouts.XLabelLayout

class AndroidXLabelLayout(root: ViewGroup) :
    XLabelLayout{

    private val view = root.add { TextView(root.context) }

    private val textDelegate = AndroidXTextLayout(view)

    override fun setText(text: String?) = textDelegate.setText(text)
    override fun getText(): String? {
        return textDelegate.getText()
    }
}