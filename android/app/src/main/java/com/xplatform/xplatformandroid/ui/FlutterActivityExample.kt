package com.xplatform.xplatformandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xplatform.xplatformandroid.R.layout
import com.xplatform.xplatformandroid.dto.Todo
import com.xplatform.xplatformandroid.flutter.MainFlutterActivity
import kotlinx.android.synthetic.main.flutter_activity_main.*

class FlutterActivityExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.flutter_activity_main)

        textview_second.text = formatTodo(intent.extras, null)

        button_flutter.setOnClickListener {
            startFlutterActivity()
        }
    }

    private fun formatTodo(bundle: Bundle?, todo: Todo?): String {
        return """
            received title: ${bundle?.get("title") ?: todo?.title ?: "no title"} 
            received description: ${bundle?.get("description") ?: todo?.description
        ?: "no description"} 
        """.trimIndent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == 100 -> receivedFromFlutter(resultCode, data)
        }
    }

    private fun startFlutterActivity() {
        startActivityForResult(
            Intent(this, MainFlutterActivity::class.java),
            100
        )
    }

    private fun receivedFromFlutter(resultCode: Int, data: Intent?) {
        textview_second.text =
            formatTodo(
                data?.extras,
                data?.extras?.get(MainFlutterActivity.FlutterResult) as Todo
            )
    }
}