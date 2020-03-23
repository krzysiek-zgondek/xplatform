package com.xplatform.xplatformandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xplatform.xplatformandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        startFlutterActivity.setOnClickListener { view ->
            startActivity(
                Intent(this, FlutterActivityExample::class.java)
            )
        }

        startFlutterFragmentActivity.setOnClickListener { view ->
            startActivity(
                Intent(this, FlutterFragmentActivityExample::class.java)
            )
        }

        startXplatformKotlin.setOnClickListener { view ->
            startActivity(
                Intent(this, XKotlinActivity::class.java)
            )
        }
    }
}
