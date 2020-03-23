package com.xplatform.xkotlin.ui.layouts

/**
 * Android's Click Listener
 * */
interface XClickableLayout {
    fun setOnClick(action: (() -> Unit)?)
}

/**
 * Shortcuts
 * */
inline var XClickableLayout.onClick: (() -> Unit)?
    get() = throw NotImplementedError("cannot get onClick action; action is set only")
    set(value) = setOnClick(value)
