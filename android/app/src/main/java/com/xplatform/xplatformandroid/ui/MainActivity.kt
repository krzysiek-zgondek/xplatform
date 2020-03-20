package com.xplatform.xplatformandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.Navigation.findNavController
import com.xplatform.xplatformandroid.R
import com.xplatform.xplatformandroid.flutter.FlutterBridging
import com.xplatform.xplatformandroid.flutter.MainFlutterFragment
import com.xplatform.xplatformandroid.dto.Todo
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val flutterFragment: MainFlutterFragment by lazy {
        supportFragmentManager.findFragmentByTag(FlutterFragmentTag) as? MainFlutterFragment
            ?: run {
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
            callFlutterMethod()
        }
    }

    private fun callFlutterMethod() {
        val todo = Todo(
            "Android Todo",
            "Received from Android"
        )
        flutterFragment.channel.navigate("/details", todo)
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
        nav_host_fragment.isVisible = false
        flutterFragmentBridgeContainer.isVisible = true

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

    fun showNativeTodoFragment(todo: Todo) {
        flutterFragmentBridgeContainer.isVisible = false
        nav_host_fragment.isVisible = true

        val bundle = bundleOf(
            "title" to todo.title,
            "description" to todo.description
        )

        findNavController(
            this,
            R.id.nav_host_fragment
        ).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

    companion object {
        const val FlutterFragmentTag = "flutter.fragment.tag"
    }
}
