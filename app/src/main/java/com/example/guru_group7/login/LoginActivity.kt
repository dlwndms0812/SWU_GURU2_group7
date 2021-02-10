package com.example.guru_group7.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.guru_group7.R
import com.example.guru_group7.recommend.RecommendActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    //Google Login result
    private val RC_SIGN_IN=9001
    //Firebase Auth
    private var firebaseAuth: FirebaseAuth?=null

    lateinit var loginBtn: Button
    lateinit var loginid:EditText
    lateinit var loginpassword:EditText
    lateinit var signuppageBtn:Button


    //[START onCreate]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val gosignup= Intent(this, SignupActivity::class.java)


        loginBtn=findViewById<Button>(R.id.loginBtn)
        loginid=findViewById<EditText>(R.id.join_id)
        loginpassword=findViewById<EditText>(R.id.Join_password)
        signuppageBtn=findViewById<Button>(R.id.signup_page_Btn)

        firebaseAuth= FirebaseAuth.getInstance();

        loginBtn.setOnClickListener{
            login()
        }
        signuppageBtn.setOnClickListener{
            startActivity(gosignup)
        }
    }
    //[END onCreate]


    //Email SignIn
    private fun login(){
        firebaseAuth!!.signInWithEmailAndPassword(loginid.text.toString(),loginpassword.text.toString())
            .addOnCompleteListener(this){
                if(it.isSuccessful) {
                    //Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                    val user=firebaseAuth?.currentUser
                    val nextpage=Intent(this, RecommendActivity::class.java)
                    startActivity(nextpage)

                } else {
                    //If sign in fails, display a message to the user
                    Toast.makeText(this, "로그인 실패",Toast.LENGTH_SHORT).show()
                }

            }

    }

}

//참고자료
//https://gigas-blog.tistory.com/77
