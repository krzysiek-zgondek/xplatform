package com.xplatform.xkotlin.ui

import com.xplatform.xkotlin.ui.layouts.XButtonLayout
import com.xplatform.xkotlin.ui.layouts.XFlatLayout
import com.xplatform.xkotlin.ui.layouts.XLabelLayout

interface XBuilder {
    fun provideFlatLayout(): XFlatLayout
    fun provideLabelLayout(): XLabelLayout
    fun provideButtonLayout(): XButtonLayout
}