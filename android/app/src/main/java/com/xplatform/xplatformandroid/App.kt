package com.xplatform.xplatformandroid

import android.app.Application
import createCachedFlutterEngine

class App : Application() {
    val activityEngine by lazy {
        createCachedFlutterEngine(FlutterBridging.CachedEngineActivityId)
    }

    val fragmentEngine by lazy {
        createCachedFlutterEngine(FlutterBridging.CachedEngineFragmentId)
    }

    override fun onCreate() {
        super.onCreate()

        activityEngine
        fragmentEngine
    }
}

