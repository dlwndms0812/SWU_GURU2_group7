package com.example.guru_group7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_group7.calendar.CalendarActivity
import com.example.guru_group7.note.NoteMainActivity
import com.example.guru_group7.recommend.RecommendActivity

class Appmain:AppCompatActivity() {
    lateinit var btn_calendar:Button
    lateinit var btn_recommend:Button
    lateinit var btn_diary:Button
    lateinit var btn_mypage:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appmain)

        btn_calendar=findViewById<Button>(R.id.btn_calendar)
        btn_recommend=findViewById<Button>(R.id.btn_recommend)
        btn_diary=findViewById<Button>(R.id.btn_diary)
        btn_mypage=findViewById<Button>(R.id.btn_myPage)

        btn_calendar.setOnClickListener {
            val go_caledar= Intent(this, CalendarActivity::class.java)
            startActivity(go_caledar)
        }

        btn_recommend.setOnClickListener {
            val go_recommend= Intent(this, RecommendActivity::class.java)
            startActivity(go_recommend)
        }

        btn_diary.setOnClickListener {
            val go_diary= Intent(this, NoteMainActivity::class.java)
            startActivity(go_diary)
        }

        btn_mypage.setOnClickListener {
            val go_mypage= Intent(this, Mypage::class.java)
            startActivity(go_mypage)
        }

    }
}