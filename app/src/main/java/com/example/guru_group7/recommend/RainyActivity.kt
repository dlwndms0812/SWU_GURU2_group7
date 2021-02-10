package com.example.guru_group7.recommend

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.R

class RainyActivity: AppCompatActivity() {
    lateinit var imgBtn_music : ImageButton
    lateinit var imgBtn_hobby : ImageButton

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rainy)

        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=J79HVjqxejs"))
            startActivity(intent)
        }
        imgBtn_hobby = findViewById<ImageButton>(R.id.imgBtn_hobby)

        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&linkClass=&barcode=9788959139309"))
            startActivity(intent)
        }

    }
}