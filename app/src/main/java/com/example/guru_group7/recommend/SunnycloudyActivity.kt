package com.example.guru_group7.recommend

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.R

//조금흐림 음악/영화/취미 선택 기능 구현
class SunnycloudyActivity: AppCompatActivity() {
    lateinit var imgBtn_music : ImageButton
    lateinit var imgBtn_hobby : ImageButton
    lateinit var imgBtn_movie : ImageButton

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sunnycloudy)

        //각 id를 통해 View의 버튼 객체 연결
        imgBtn_music = findViewById<ImageButton>(R.id.imgBtn_music)
        imgBtn_hobby = findViewById<ImageButton>(R.id.imgBtn_hobby)
        imgBtn_movie=findViewById<ImageButton>(R.id.imgBtn_movie)

        //음악 버튼 누르면, 연결된 콘텐츠 링크로 이동
        imgBtn_music.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tqqgWHOo5Kw"))
            startActivity(intent)
        }

        //취미 버튼 누르면, 연결된 콘텐츠 링크로 이동
        imgBtn_hobby.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=cWGM0oU58Q0"))
            startActivity(intent)
        }

        //영화 버튼 누르면, 추천 영화 목록 보여줌
        imgBtn_movie.setOnClickListener{
            startActivity(Intent(this,SunnyCloudyMovie::class.java))
        }
    }
}