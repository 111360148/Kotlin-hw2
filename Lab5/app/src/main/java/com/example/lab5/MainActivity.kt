package com.example.lab5

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 設置邊距並記錄 onCreate 日誌
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.e("MainActivity", "onCreate")

        // 設定 ViewPager2
        findViewById<ViewPager2>(R.id.viewPager2).apply {
            adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
            offscreenPageLimit = 1
        }
    }

    // 可選擇不記錄其他生命周期的 Log 或保留少數關鍵的
    override fun onRestart() = super.onRestart().also { Log.e("MainActivity", "onRestart") }
    override fun onStart() = super.onStart().also { Log.e("MainActivity", "onStart") }
    override fun onResume() = super.onResume().also { Log.e("MainActivity", "onResume") }
    override fun onPause() = super.onPause().also { Log.e("MainActivity", "onPause") }
    override fun onStop() = super.onStop().also { Log.e("MainActivity", "onStop") }
    override fun onDestroy() = super.onDestroy().also { Log.e("MainActivity", "onDestroy") }
}
