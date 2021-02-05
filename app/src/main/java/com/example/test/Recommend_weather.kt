package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Recommend_weather : AppCompatActivity() {

    lateinit var btn_sunny : androidx.appcompat.widget.AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_weather)

        btn_sunny = findViewById(R.id.btn_sunny)

        btn_sunny.setOnClickListener {
            var intent = Intent(this, Recommend_media::class.java)
            startActivity(intent)
        }
    }
}