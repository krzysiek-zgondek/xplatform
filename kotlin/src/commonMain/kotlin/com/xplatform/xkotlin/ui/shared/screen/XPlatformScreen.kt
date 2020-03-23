package com.xplatform.xkotlin.ui.shared.screen

import com.xplatform.xkotlin.ui.layouts.XFlatLayout.Orientation.Vertical
import com.xplatform.xkotlin.ui.layouts.XLayoutGroup
import com.xplatform.xkotlin.ui.layouts.button
import com.xplatform.xkotlin.ui.layouts.flatLayout
import com.xplatform.xkotlin.ui.layouts.label
import com.xplatform.xkotlin.ui.layouts.onClick
import com.xplatform.xkotlin.ui.layouts.orientation
import com.xplatform.xkotlin.ui.layouts.text
import com.xplatform.xkotlin.ui.shared.dto.XTodo

class XPlatformScreen(
    private val ui: XLayoutGroup,
    private val contract: XScreenNativeContract
) {
    interface XScreenNativeContract {
        fun onTodoReceived(todo: XTodo)
    }

    fun ui(state: XScreenState) {
        ui.flatLayout {
            orientation = Vertical

            label {
                text = "received: ${state.todo?.toString() ?: "no data"}"
            }

            button {
                text = "Call android native method"
                onClick = {
                    contract.onTodoReceived(
                        todo = generateTodo()
                    )
                }
            }
        }
    }

    private fun generateTodo(): XTodo {
        return XTodo(
            title = "XPlatform Todo",
            description = "received from XPlatform"
        )
    }
}