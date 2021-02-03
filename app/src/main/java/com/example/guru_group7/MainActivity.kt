package com.example.guru_group7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    //Google Login result
    private val RC_SIGN_IN=9001
    //Firebase Auth
    private var firebaseAuth: FirebaseAuth?=null

    //[START onCreate]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth= FirebaseAuth.getInstance();

        googleLoginBtn.setOnClickListener{
            //회원가입 또는 로그인 함수 호출부
        }
    }
    //[END onCreate]

    //EmailCreate
    private fun createEmail(){
        firebaseAuth!!.createUserWithEmailAndPassword(edit_email.text.toString(),edit_password.text.toString())
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    //Sign in success, update UI with the signed-in user's information
                    val user=firebaseAuth?.currentUser
                    Toast.makeText(this,"Authentication success.",Toast.LENGTH_SHORT).show()
                } else{
                    //If sign in fails, display a message to the user
                    Toast.makeText(this,"Authentication failed.",Toast.LENGTH_SHORT).show()
                }
            }
    }

    //Email SignIn
    private fun loginEmail(){
        firebaseAuth!!.signInWithEmailAndPassword(edit_email.text.toString(),edit_password.text.toString())
            .addOnCompleteListener(this){
                if(it.isSuccessful) {
                    //Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"signInWithEmail success.",Toast.LENGTH_SHORT).show()
                    val user=firebaseAuth?.currentUser
                } else {
                    //If sign in fails, display a message to the user
                    Toast.makeText(this, "signInWithEmail failed",Toast.LENGTH_SHORT).show()
                }
                }
            }
    }

//참고자료
//https://gigas-blog.tistory.com/77

}