package com.xplatform.xplatformandroid.xkotlin.layouts

import android.view.ViewGroup
import android.widget.Button
import com.xplatform.xkotlin.ui.layouts.XButtonLayout
import com.xplatform.xplatformandroid.xkotlin.add

class AndroidXButtonLayout(root: ViewGroup) :
    XButtonLayout {

    private val view = root.add { Button(root.context) }

    private val textDelegate = AndroidXTextLayout(view)
    private val clickDelegate = AndroidXClickableLayout(view)

    override fun setText(text: String?) = textDelegate.setText(text)
    override fun getText(): String? {
        return textDelegate.getText()
    }

    override fun setOnClick(action: (() -> Unit)?) {
        clickDelegate.setOnClick(action)
    }
}