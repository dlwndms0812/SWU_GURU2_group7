package com.example.guru_group7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import com.example.guru_group7.login.LoginActivity
import com.example.guru_group7.login.PasswordActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity:AppCompatActivity() {
    val SPLASH_VIEW_TIME : Long = 3000 //2초간 스플래시 화면 보여줌

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({ //delay를 위한 handler
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)

    }
}