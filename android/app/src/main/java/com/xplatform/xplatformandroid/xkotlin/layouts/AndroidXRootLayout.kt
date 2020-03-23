package com.xplatform.xplatformandroid.xkotlin.layouts

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.xplatform.xkotlin.impl.XBuilderAndroid
import com.xplatform.xkotlin.ui.XBuilder
import com.xplatform.xkotlin.ui.layouts.XRootLayout

class AndroidXRootLayout(val root: ViewGroup) :
    XRootLayout {
    override fun builder(): XBuilder {
        return XBuilderAndroid(root)
    }
}

inline fun AppCompatActivity.rootLayout(body: FrameLayout.() -> Unit = {}): XRootLayout {
    val view = FrameLayout(this).apply(body)
    val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    addContentView(
        view,
        params
    )

    return AndroidXRootLayout(view)
}