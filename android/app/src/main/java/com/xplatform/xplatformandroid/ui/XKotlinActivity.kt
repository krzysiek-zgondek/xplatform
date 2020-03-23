package com.xplatform.xplatformandroid.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xplatform.xkotlin.ui.shared.dto.XTodo
import com.xplatform.xkotlin.ui.shared.screen.XPlatformScreen
import com.xplatform.xkotlin.ui.shared.screen.XPlatformScreen.XScreenNativeContract
import com.xplatform.xkotlin.ui.shared.screen.XScreenState
import com.xplatform.xplatformandroid.xkotlin.layouts.rootLayout

class XKotlinActivity : AppCompatActivity(), XScreenNativeContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screen = XPlatformScreen(
            ui = rootLayout(),
            contract = this
        )

        screen.ui(
            state = XScreenState(
                todo = XTodo(
                    title = "Android Todo",
                    description = "received from Android"
                )
            )
        )

    }

    override fun onTodoReceived(todo: XTodo) {
        Toast.makeText(this, "$todo", Toast.LENGTH_LONG).show()
    }
}
