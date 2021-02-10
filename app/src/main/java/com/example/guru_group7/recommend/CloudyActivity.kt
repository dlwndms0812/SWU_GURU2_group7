package com.example.guru_group7.recommend

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.R

//cloudy와 cloudysunny랑 바꼈을수도 있음
class CloudyActivity: AppCompatActivity() {
    lateinit var imgBtn_music : ImageButton
    lateinit var imgBtn_hobby : ImageButton
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cloudy)

        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=EDsPY0d9s4E"))
            startActivity(intent)
        }
        imgBtn_hobby = findViewById<ImageButton>(R.id.imgBtn_hobby)

        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://beadsn.com/product/%EC%A7%91%EC%BD%95-%EB%B9%84%EC%A6%88%EA%B3%B5%EC%98%88%EC%9E%AC%EB%A3%8C-diy-%EB%82%98%EB%A7%8C%EC%9D%98-%ED%8C%94%EC%B0%8C-%ED%82%A4%EB%A7%81-%EB%A7%8C%EB%93%A4%EA%B8%B0-%EB%B9%84%EC%A6%88%EC%9E%AC%EB%A3%8C-%EB%B9%84%EC%A6%88%EA%B5%AC%EC%8A%AC-%EB%AA%A8%EC%9D%8C%EC%A0%84/2981/category/1/display/3/"))
            startActivity(intent)
        }
    }
}