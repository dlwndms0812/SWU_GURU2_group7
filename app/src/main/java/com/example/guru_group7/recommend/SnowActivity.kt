package com.example.guru_group7.recommend

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.R

class SnowActivity: AppCompatActivity() {
    lateinit var imgBtn_music : ImageButton
    lateinit var imgBtn_hobby : ImageButton
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snow)

        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=4Ei4dHzLiDE"))
            startActivity(intent)
        }
        imgBtn_hobby = findViewById<ImageButton>(R.id.imgBtn_hobby)

        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://smartstore.naver.com/alphaway/products/5194758670"))
            startActivity(intent)
        }

    }
}