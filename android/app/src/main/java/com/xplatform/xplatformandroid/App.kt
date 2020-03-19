package com.xplatform.xplatformandroid

import android.app.Application
import createCachedFlutterEngine

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        createCachedFlutterEngine(FlutterBridging.CachedEngineActivityId)
        createCachedFlutterEngine(FlutterBridging.CachedEngineFragmentId)
    }
}

