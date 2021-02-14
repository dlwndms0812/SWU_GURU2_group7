package com.example.guru_group7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

//스플래시 화면 구현
class SplashActivity : AppCompatActivity() {
    val SPLASH_VIEW_TIME: Long = 3000 //3초간 스플래시 화면 보여줌

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({ //delay를 위한 handler
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)

    }
}