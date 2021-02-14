package com.example.guru_group7.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.R
import com.google.firebase.auth.FirebaseAuth

class PasswordActivity:AppCompatActivity() {
    lateinit var gologinBtn:Button
    lateinit var change_Btn:Button
    lateinit var email:EditText

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)

        gologinBtn=findViewById<Button>(R.id.gologinBtn)
        change_Btn=findViewById<Button>(R.id.change_Btn)
        email=findViewById<EditText>(R.id.email)

        val go_login= Intent(this, LoginActivity::class.java)

        gologinBtn.setOnClickListener {
            startActivity(go_login)
        }


        change_Btn.setOnClickListener {
            changePassword()
        }
    }

    //비밀번호 변경구현
    //버튼 누르면 해당 이메일로 비밀번호 변경 메일 전송
    fun changePassword(){
        FirebaseAuth.getInstance().sendPasswordResetEmail(email.text.toString())
                .addOnCompleteListener{task->
                    if(task.isSuccessful){
                        Toast.makeText(this, "비밀번호 변경 메일을 전송했습니다",Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(this,task.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
    }

}