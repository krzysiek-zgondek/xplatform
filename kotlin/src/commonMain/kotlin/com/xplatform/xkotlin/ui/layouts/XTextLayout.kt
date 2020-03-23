package com.xplatform.xkotlin.ui.layouts

/**
 * View can set and get text
 * */
interface XTextLayout{
    fun setText(text: String?)
    fun getText(): String?
}

/**
 * Shortcuts
 * */
inline var XTextLayout.text: String?
    get() = getText()
    set(value) = setText(value)
