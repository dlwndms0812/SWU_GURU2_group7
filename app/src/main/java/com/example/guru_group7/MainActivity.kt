package com.example.guru_group7

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.login.LoginActivity
import com.example.guru_group7.login.PasswordActivity
import com.example.guru_group7.note.NoteMainActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //앱이 실행되자 마자 로그인 화면으로 이동
        val go_login= Intent(this, LoginActivity::class.java)
        startActivity(go_login)
    }


}