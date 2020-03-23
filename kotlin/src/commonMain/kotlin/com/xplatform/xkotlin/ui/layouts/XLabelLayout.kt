package com.xplatform.xkotlin.ui.layouts

/**
 * Android's TextView
 * */
interface XLabelLayout: XTextLayout

/**
 * Entry point dsl for label layout
 * */
inline fun XLayoutGroup.label(body: XLabelLayout.() -> Unit): XLabelLayout {
    return builder().provideLabelLayout().apply(body)
}