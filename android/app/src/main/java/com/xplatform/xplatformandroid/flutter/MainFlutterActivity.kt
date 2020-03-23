package com.xplatform.xplatformandroid.flutter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.xplatform.xplatformandroid.dto.Todo
import com.xplatform.xplatformandroid.dto.Todo.Companion.fromJson
import com.xplatform.xplatformandroid.flutter.ChannelMain.Companion.Flutter
import com.xplatform.xplatformandroid.flutter.ChannelMain.Companion.registerChannel
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.Result

class MainFlutterActivity : FlutterActivity() {
    var channel: ChannelMain? = null

    override fun onStart() {
        super.onStart()
        callFlutterMethod()
    }

    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return FlutterEngineCache.getInstance()[FlutterBridging.CachedEngineActivityId]
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        if (channel == null) {
            channel = registerChannel(
                flutterEngine.dartExecutor.binaryMessenger
            ) { methodCall: MethodCall?, result: Result? ->
                this.mainChannelReceiver(
                    methodCall,
                    result
                )
            }
        }
    }

    private fun callFlutterMethod() {
        val todo = Todo(
            "Android Todo",
            "Received from Android"
        )
        channel?.navigate("/details", todo)
    }

    /* This is unsafe as it can be called outside lifecycle but just for this example i will omit
     * all the lifecycle logic
     * */
    private fun mainChannelReceiver(
        methodCall: MethodCall?,
        result: Result?
    ) {
        when (methodCall?.method) {
            Flutter.resultMethod -> executeNativeTodo(
                fromJson(methodCall.arguments.toString())
            )
            else -> throw IllegalArgumentException()
        }
        Toast.makeText(context, methodCall.arguments.toString(), Toast.LENGTH_LONG).show()
    }

    /*You should never call host like this, but for the sake of simplicity lets accept that*/
    private fun executeNativeTodo(todo: Todo) {
        val intent = Intent().apply {
            putExtra(FlutterResult, todo)
        }

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val FlutterResult = "flutter.result"
    }
}