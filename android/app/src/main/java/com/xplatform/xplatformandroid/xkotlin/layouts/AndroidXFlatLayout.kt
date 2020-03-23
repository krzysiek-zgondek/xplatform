package com.xplatform.xplatformandroid.xkotlin.layouts

import android.view.ViewGroup
import android.widget.LinearLayout
import com.xplatform.xkotlin.ui.layouts.XFlatLayout
import com.xplatform.xkotlin.impl.XBuilderAndroid
import com.xplatform.xplatformandroid.xkotlin.add
import com.xplatform.xkotlin.ui.XBuilder
import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Gravity
import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Gravity.Center
import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Gravity.Left
import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Orientation
import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Orientation.Horizontal
import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Orientation.Vertical

class AndroidXFlatLayout(root: ViewGroup) :
    XFlatLayout {
    private val view = root.add { LinearLayout(root.context) }

    override fun builder(): XBuilder {
        return XBuilderAndroid(view)
    }

    override fun setOrientation(orientation: Orientation) {
        view.orientation = when (orientation) {
            Vertical -> LinearLayout.VERTICAL
            Horizontal -> LinearLayout.HORIZONTAL
        }
    }

    override fun getOrientation(): Orientation {
        return when (view.orientation) {
            LinearLayout.VERTICAL -> Vertical
            else -> Horizontal
        }
    }

    override fun setGravity(gravity: Gravity) {
        view.gravity = when (gravity) {
            Left -> android.view.Gravity.START
            Center -> android.view.Gravity.CENTER
        }
    }

    override fun getGravity(): Gravity {
        throw NotImplementedError()
    }
}