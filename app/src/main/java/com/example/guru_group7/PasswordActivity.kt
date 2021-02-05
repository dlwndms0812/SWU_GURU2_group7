package com.example.guru_group7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        val go_login= Intent(this,LoginActivity::class.java)
        startActivity(go_login)

        change_Btn.setOnClickListener {
            changePassword()
        }
    }

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