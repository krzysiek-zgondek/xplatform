package com.xplatform.xkotlin.ui.layouts

import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Gravity
import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Orientation

/**
 * Android's LinearLayout
 * */
interface XFlatLayout :
    XLayoutGroup {
    enum class Orientation { Vertical, Horizontal }
    enum class Gravity { Left, Center }

    fun setOrientation(orientation: Orientation)
    fun getOrientation(): Orientation

    fun setGravity(gravity: Gravity)
    fun getGravity(): Gravity
}

/**
 * Entry point dsl for flat layout
 * */
inline fun XLayoutGroup.flatLayout(body: XFlatLayout.() -> Unit): XFlatLayout {
    return builder().provideFlatLayout().apply(body)
}

/**
 * Shortcuts
 * */
inline var XFlatLayout.orientation: Orientation
    get() = getOrientation()
    set(value) = setOrientation(value)

inline var XFlatLayout.gravity: Gravity
    get() = getGravity()
    set(value) = setGravity(value)
