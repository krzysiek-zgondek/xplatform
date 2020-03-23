package com.xplatform.xplatformandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xplatform.xplatformandroid.R.layout
import kotlinx.android.synthetic.main.flutter_fragment_activity_main.*

class FlutterFragmentActivityExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.flutter_fragment_activity_main)
        setSupportActionBar(toolbar)
    }
}