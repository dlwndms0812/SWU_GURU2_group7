package com.example.guru_group7.recommend

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.R

class SunnyActivity:AppCompatActivity() {
    lateinit var imgBtn_music : ImageButton

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sunny)

        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=HGrP1qdE5Rw"))
            startActivity(intent)
        }
    }

}