package com.example.guru_group7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.login.LoginActivity
import com.example.guru_group7.login.PasswordActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_note_main.*

class Mypage:AppCompatActivity() {
    lateinit var btn_changepassword:Button
    lateinit var btn_logout:Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage)

        btn_changepassword=findViewById<Button>(R.id.btn_changePasswd)
        btn_logout=findViewById<Button>(R.id.btn_logout)

        btn_changepassword.setOnClickListener {
            val changepassword= Intent(this, PasswordActivity::class.java)
            startActivity(changepassword)
        }

        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val go_login= Intent(this, LoginActivity::class.java)
            startActivity(go_login)
        }
    }
}