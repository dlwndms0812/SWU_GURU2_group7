package com.example.guru_group7.recommend

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.R

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

        //sunny 버튼을 눌렀을때
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



    }


}