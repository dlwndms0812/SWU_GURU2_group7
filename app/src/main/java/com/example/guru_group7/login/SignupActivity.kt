package com.example.guru_group7.login

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.guru_group7.R
import com.google.firebase.auth.FirebaseAuth

// 회원가입 기능 구현
class SignupActivity() : AppCompatActivity(), Parcelable {
    private val RC_SIGN_IN = 9001

    //Firebase Auth 연결
    private var firebaseAuth: FirebaseAuth? = null

    lateinit var joinid: EditText
    lateinit var joinpassword: EditText
    lateinit var signupBtn: Button
    lateinit var deleteBtn: Button
    lateinit var join_pwck: EditText
    lateinit var check_button: AppCompatButton

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        val gologin = Intent(this, LoginActivity::class.java)

        //각 id를 통해 View의 버튼 객체 연결
        joinid = findViewById<EditText>(R.id.join_id)
        joinpassword = findViewById<EditText>(R.id.Join_password)
        signupBtn = findViewById<Button>(R.id.signup_Btn)
        deleteBtn = findViewById<Button>(R.id.delete_Btn)
        join_pwck = findViewById<EditText>(R.id.join_pwck)
        check_button = findViewById<AppCompatButton>(R.id.check_button)

        //회원가입 버튼 터치
        signupBtn.setOnClickListener {
            //입력한 비밀번호와 비밀번호 확인에 입력한 비밀번호가 같을 시 회원가입 성공
            if (join_pwck.text.toString().equals(join_pwck.text.toString())) {
                sign()
                startActivity(gologin)
            } else {
                Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_LONG).show()
            }
        }

        //취소하기 버튼 터치
        deleteBtn.setOnClickListener {
            startActivity(gologin)
        }
    }

    //회원 가입 구현
    private fun sign() {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(joinid.text.toString(), joinpassword.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "가입 성공", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this, "가입 실패", Toast.LENGTH_LONG).show()
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