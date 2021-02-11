package com.example.guru_group7.recommend

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.guru_group7.CalendarFragment
import com.example.guru_group7.MediaFragment
import com.example.guru_group7.NoteFragment
import com.example.guru_group7.R
import kotlinx.android.synthetic.main.recommend_weather.*
import com.example.guru_group7.login.LoginActivity
import com.example.guru_group7.login.PasswordActivity
import com.example.guru_group7.login.SignupActivity
import com.google.firebase.auth.FirebaseAuth

class RecommendActivity:AppCompatActivity() {
    lateinit var btn_sunny: ImageButton
    lateinit var btn_rainy:ImageButton
    lateinit var btn_cloudy:ImageButton
    lateinit var btn_sunnycloudy:ImageButton
    lateinit var btn_snowy:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recommend_weather)

        btn_sunny = findViewById<ImageButton>(R.id.btn_sunny)
        btn_rainy = findViewById<ImageButton>(R.id.btn_rainy)
        btn_cloudy = findViewById<ImageButton>(R.id.btn_cloudy)
        btn_sunnycloudy = findViewById<ImageButton>(R.id.btn_sunCloudy)
        btn_snowy = findViewById<ImageButton>(R.id.btn_snowy)

        btn_sunny.setOnClickListener {
            startActivity(Intent(this, SunnyActivity::class.java))
        }
        btn_rainy.setOnClickListener {
            val go_rainy = Intent(this, RainyActivity::class.java)
            startActivity(go_rainy)
        }
        btn_cloudy.setOnClickListener {
            val go_cloudy = Intent(this, CloudyActivity::class.java)
            startActivity(go_cloudy)
        }
        btn_sunnycloudy.setOnClickListener {
            val go_sunnycloudy = Intent(this, SunnycloudyActivity::class.java)
            startActivity(go_sunnycloudy)
        }
        btn_snowy.setOnClickListener {
            val go_snowy = Intent(this, SnowActivity::class.java)
            startActivity(go_snowy)
        }

        // 추천페이지 fragment val recommendFragment = RecommendFragment()
        val calendarFragment = CalendarFragment()
        val noteFragment = NoteFragment()
        val mediaFragment = MediaFragment()
        val mypageFragment= MypageFragment()


        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.calendarTab -> {
                    // Log.d("태그","달력")
                    makeCurrentFragment(calendarFragment)
                }
                R.id.diaryTab -> {
                    // Log.d("태그","다이어리")
                    makeCurrentFragment(noteFragment)
                }
                R.id.mediaTab -> {
                    // Log.d("태그","미디어")
                    makeCurrentFragment(mediaFragment)
                }
                R.id.myPageTab->{
                    //Log.d("태그","마이페이지")
                    makeCurrentFragment(mypageFragment)
                }
            }
            true
        }

    }

        private fun makeCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout, fragment)
                commit()
            }
}