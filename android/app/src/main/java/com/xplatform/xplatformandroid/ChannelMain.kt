package com.xplatform.xplatformandroid

import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result

class ChannelMain @PublishedApi internal constructor(val channel: MethodChannel) {
    fun navigate(routePath: String, args: Any) {
        when(args){
            is Todo -> channel.invokeMethod(navigateMethod, args.parseToJson())
        }
    }

    companion object {
        const val name: String = "com.xplatform.flutter/app"

        const val navigateMethod: String = "navigate"

        @JvmStatic
        inline fun registerChannel(
            messenger: BinaryMessenger,
            crossinline invoke: (MethodCall, Result) -> Unit
        ): ChannelMain {
            val channel = MethodChannel(messenger, name).apply {
                setMethodCallHandler { call, result ->
                    invoke(call, result)
                }
            }

            return ChannelMain(channel)
        }
    }
}