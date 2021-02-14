package com.example.guru_group7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.calendar.CalendarActivity
import com.example.guru_group7.diary.DiaryMainActivity
import com.example.guru_group7.recommend.RecommendActivity

//메인 메뉴화면 기능 구현
class Appmain:AppCompatActivity() {
    lateinit var btn_calendar:Button
    lateinit var btn_recommend:Button
    lateinit var btn_diary:Button
    lateinit var btn_mypage:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appmain)

        //각 id를 통해 View의 버튼 객체 연결
        btn_calendar=findViewById<Button>(R.id.btn_calendar)
        btn_recommend=findViewById<Button>(R.id.btn_recommend)
        btn_diary=findViewById<Button>(R.id.btn_diary)
        btn_mypage=findViewById<Button>(R.id.btn_myPage)

        //칭찬 캘린더 버튼 누르면, 해당 화면으로 이동
        btn_calendar.setOnClickListener {
            val go_caledar= Intent(this, CalendarActivity::class.java)
            startActivity(go_caledar)
        }

        //마음 알약(콘텐츠 추천)버튼 누르면, 해당 화면으로 이동
        btn_recommend.setOnClickListener {
            val go_recommend= Intent(this, RecommendActivity::class.java)
            startActivity(go_recommend)
        }

        //마음 다이어리 버튼 누르면, 해당 화면으로 이동
        btn_diary.setOnClickListener {
            val go_diary= Intent(this, DiaryMainActivity::class.java)
            startActivity(go_diary)
        }

        //마이페이지 버튼 누르면, 해당 화면으로 이동
        btn_mypage.setOnClickListener {
            val go_mypage= Intent(this, Mypage::class.java)
            startActivity(go_mypage)
        }

    }
}