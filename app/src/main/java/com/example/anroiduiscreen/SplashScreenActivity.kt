package com.example.anroiduiscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.anroiduiscreen.onboardscreen.OnBoardScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splashscreen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnBoardScreenActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}