package com.example.lab6

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnToast = findViewById<Button>(R.id.btnToast)
        val btnSnackBar = findViewById<Button>(R.id.btnSnackBar)
        val btnDialog1 = findViewById<Button>(R.id.btnDialog1)
        val btnDialog2 = findViewById<Button>(R.id.btnDialog2)
        val btnDialog3 = findViewById<Button>(R.id.btnDialog3)
        val btnCountdown = findViewById<Button>(R.id.btnCountdown)
        val btnExit = findViewById<Button>(R.id.btnExit)

        val item = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

        // 預設 Toast
        btnToast.setOnClickListener {
            showToast("預設 Toast")
        }

        // 長按事件
        btnToast.setOnLongClickListener {
            showCenteredToast("長按事件觸發")
            true
        }

        // SnackBar 功能
        btnSnackBar.setOnClickListener {
            Snackbar.make(it, "按鈕式 Snackbar，這是多行訊息顯示範例。\n可以顯示更長的訊息內容。", Snackbar.LENGTH_LONG)
                .setAction("按鈕") {
                    showToast("已回應")
                }.show()
        }

        // AlertDialog with buttons
        btnDialog1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("按鈕式 AlertDialog")
                .setMessage("AlertDialog 內容")
                .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
                .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
                .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
                .show()
        }

        // 列表式 AlertDialog
        btnDialog2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("列表式 AlertDialog")
                .setItems(item) { _, i -> showToast("你選的是${item[i]}") }
                .show()
        }

        // 單選式 AlertDialog
        btnDialog3.setOnClickListener {
            var position = 0
            AlertDialog.Builder(this)
                .setTitle("單選式 AlertDialog")
                .setSingleChoiceItems(item, 0) { _, i -> position = i }
                .setPositiveButton("確定") { _, _ -> showToast("你選的是${item[position]}") }
                .show()
        }

        // 倒數計時功能
        btnCountdown.setOnClickListener {
            object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    showToast("剩餘時間: ${millisUntilFinished / 1000} 秒")
                }

                override fun onFinish() {
                    showToast("時間結束！")
                }
            }.start()
        }

        // 退出確認功能
        btnExit.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("退出應用程式")
                .setMessage("你確定要退出嗎？")
                .setPositiveButton("是") { _, _ -> finish() }
                .setNegativeButton("否", null)
                .show()
        }
    }

    // 標準 Toast 顯示
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    // 中央顯示 Toast
    private fun showCenteredToast(msg: String) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
