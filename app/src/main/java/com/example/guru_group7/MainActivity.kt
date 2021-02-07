package com.example.guru_group7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.login.LoginActivity

class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val go_login= Intent(this, LoginActivity::class.java)
        startActivity(go_login)
    }
}