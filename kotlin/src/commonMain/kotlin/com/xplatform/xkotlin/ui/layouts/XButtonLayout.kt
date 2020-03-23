package com.xplatform.xkotlin.ui.layouts

/**
 * Android's Button
 * */
interface XButtonLayout :
    XClickableLayout,
    XTextLayout

/**
 * Entry point dsl for flat layout
 * */
inline fun XLayoutGroup.button(body: XButtonLayout.() -> Unit): XButtonLayout {
    return builder().provideButtonLayout().apply(body)
}