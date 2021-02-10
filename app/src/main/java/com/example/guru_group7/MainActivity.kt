package com.example.guru_group7

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.login.LoginActivity
import com.example.guru_group7.login.PasswordActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val go_login= Intent(this, LoginActivity::class.java)
        startActivity(go_login)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.action_changePasswd -> {
                val go_changePasswd = Intent(this, PasswordActivity::class.java)
                startActivity(go_changePasswd)
                return true
            }

            R.id.action_logout->{
                FirebaseAuth.getInstance().signOut()
                val go_login= Intent(this, LoginActivity::class.java)
                startActivity(go_login)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}