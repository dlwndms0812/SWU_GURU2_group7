package com.example.guru_group7.login

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.R
import com.google.firebase.auth.FirebaseAuth

class SignupActivity() :AppCompatActivity(), Parcelable {
    private val RC_SIGN_IN = 9001

    //Firebase Auth
    private var firebaseAuth: FirebaseAuth? = null

    lateinit var joinid: EditText
    lateinit var joinpassword: EditText
    lateinit var signupBtn: Button
    lateinit var deleteBtn: Button
    lateinit var join_pwck:EditText

    constructor(parcel: Parcel) : this() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        val gologin = Intent(this, LoginActivity::class.java)

        joinid = findViewById<EditText>(R.id.join_id)
        joinpassword = findViewById<EditText>(R.id.Join_password)
        signupBtn = findViewById<Button>(R.id.signup_Btn)
        deleteBtn = findViewById<Button>(R.id.delete_Btn)
        join_pwck=findViewById<EditText>(R.id.join_pwck)

            signupBtn.setOnClickListener {
                    sign()
                    startActivity(gologin)
            }

        deleteBtn.setOnClickListener {
            startActivity(gologin)
        }
    }

    //회원 가입 구현
    private fun sign(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(joinid.text.toString(),joinpassword.text.toString())
                .addOnCompleteListener {task-> Toast.makeText(this, "가입 성공", Toast.LENGTH_LONG).show()
                    if(task.isSuccessful) {


                    } else {
                        //Toast.makeText(this, "가입 실패", Toast.LENGTH_LONG).show()
                }
            }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SignupActivity> {
        override fun createFromParcel(parcel: Parcel): SignupActivity {
            return SignupActivity(parcel)
        }

        override fun newArray(size: Int): Array<SignupActivity?> {
            return arrayOfNulls(size)
        }
    }

}