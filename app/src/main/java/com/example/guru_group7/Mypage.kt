package com.example.guru_group7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.login.LoginActivity
import com.example.guru_group7.login.PasswordActivity
import com.google.firebase.auth.FirebaseAuth

class Mypage : AppCompatActivity() {
    lateinit var btn_changepassword: Button
    lateinit var btn_logout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage)

        btn_changepassword = findViewById<Button>(R.id.btn_changePasswd)
        btn_logout = findViewById<Button>(R.id.btn_logout)

        val go_login = Intent(this, LoginActivity::class.java)

        //비밀번호변경 버튼을 누르면 비밀번호 변경페이지로 이동
        btn_changepassword.setOnClickListener {
            password()
        }

        //로그아웃 버튼을 누르면 로그아웃이 되고 로그인화면으로 이동
        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
            startActivity(go_login)
        }
    }

    private fun password() {
        val changepassword = Intent(this, PasswordActivity::class.java)
        startActivity(changepassword)

    }
}