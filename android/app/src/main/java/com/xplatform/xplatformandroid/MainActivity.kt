package com.xplatform.xplatformandroid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val flutterFragment: MainFlutterFragment by lazy {
        supportFragmentManager.findFragmentByTag(FlutterFragmentTag) as? MainFlutterFragment ?: run {
            initFlutterBridging()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initFlutterBridging()

        fab.setOnClickListener { view ->
            startFlutterActivity()
        }

        fab2.setOnClickListener { view ->
            startFlutterFragment()

            val todo = Todo("Android Todo", "Received from Android")
            flutterFragment.channel.navigate("/details", todo)
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        flutterFragment.onPostResume()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        flutterFragment.onNewIntent(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        flutterFragment.onBackPressed()
    }

    override fun onUserLeaveHint() {
        flutterFragment.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment.onTrimMemory(level)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        flutterFragment.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initFlutterBridging(): MainFlutterFragment {
        return MainFlutterFragment().also {
            addFlutterFragmentBridging(it as Fragment)
        }
    }

    private fun addFlutterFragmentBridging(fragment: Fragment) {
        supportFragmentManager.commit {
            add(
                R.id.flutterFragmentBridgeContainer,
                fragment,
                FlutterFragmentTag
            )
            hide(fragment)
        }
    }

    private fun startFlutterFragment() {
        supportFragmentManager.commit {
            addToBackStack("show")
            show(flutterFragment as Fragment)
        }
    }

    private fun startFlutterActivity() {
        val intent = FlutterActivity
            .withCachedEngine(FlutterBridging.CachedEngineActivityId)
            .build(this)
        startActivity(intent)
    }

    companion object {
        const val FlutterFragmentTag = "flutter.fragment.tag"
    }
}
