package com.xplatform.xkotlin.impl

import android.view.ViewGroup
import com.xplatform.xkotlin.ui.XBuilder
import com.xplatform.xkotlin.ui.layouts.XButtonLayout
import com.xplatform.xkotlin.ui.layouts.XFlatLayout
import com.xplatform.xkotlin.ui.layouts.XLabelLayout
import com.xplatform.xplatformandroid.xkotlin.layouts.AndroidXButtonLayout
import com.xplatform.xplatformandroid.xkotlin.layouts.AndroidXFlatLayout
import com.xplatform.xplatformandroid.xkotlin.layouts.AndroidXLabelLayout

class XBuilderAndroid(val root: ViewGroup) :
    XBuilder {
    override fun provideLabelLayout(): XLabelLayout {
        return AndroidXLabelLayout(root)
    }

    override fun provideButtonLayout(): XButtonLayout {
        return AndroidXButtonLayout(root)
    }

    override fun provideFlatLayout(): XFlatLayout {
        return AndroidXFlatLayout(root)
    }
}