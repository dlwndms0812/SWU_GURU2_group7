package com.example.guru_group7

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

//cloudy와 cloudysunny랑 바꼈을수도 있음
class CloudyActivity: AppCompatActivity() {
    lateinit var imgBtn_music : ImageButton

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cloudy)

        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=EDsPY0d9s4E"))
            startActivity(intent)
        }

    }
}