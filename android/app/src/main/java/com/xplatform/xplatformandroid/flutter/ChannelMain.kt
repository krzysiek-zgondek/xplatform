package com.xplatform.xplatformandroid.flutter

import com.xplatform.xplatformandroid.dto.Todo
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result

class ChannelMain @PublishedApi internal constructor(val channel: MethodChannel) {
    fun navigate(routePath: String, args: Any) {
        when(args){
            is Todo -> channel.invokeMethod(Native.navigateMethod, args.parseToJson())
        }
    }

    companion object {
        const val name: String = "com.xplatform.flutter/app"

        object Native{
            const val navigateMethod: String = "navigate"
        }

        object Flutter{
            const val resultMethod: String = "result"
        }

        @JvmStatic
        inline fun registerChannel(
            messenger: BinaryMessenger,
            crossinline invoke: (MethodCall, Result) -> Unit
        ): ChannelMain {
            val channel = MethodChannel(messenger,
                name
            ).apply {
                setMethodCallHandler { call, result ->
                    invoke(call, result)
                }
            }

            return ChannelMain(channel)
        }
    }
}