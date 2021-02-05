package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Recommend_media : AppCompatActivity() {

    lateinit var imgBtn_music : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_media)

        imgBtn_music = findViewById<Button>(R.id.imgBtn_music)

        imgBtn_music.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

}