package com.example.guru_group7.diary

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.guru_group7.R
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// xml에서 각종 정보를 받아 옴
import kotlinx.android.synthetic.main.activity_add_diary.*

// 마음 다이어리 추가 기능
class AddDiaryActivity : AppCompatActivity() {
    private var id = 0
    private var selMood = 0
    // 마음 날씨에 따른 메인 화면 아이콘을 변경하기 위한 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_diary)

        val current = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        // 작성 시작 시간을 받아 옴

        tvDate.text = current
        // 작성 시작 시간을 기본 시간으로 설정

        val clickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.mood_sunny -> selMood = 1
                R.id.mood_cloudy -> selMood = 2
                R.id.mood_cloud -> selMood = 3
                R.id.mood_rainy -> selMood = 4
                R.id.mood_snowy -> selMood = 5
                R.id.dateBtn -> {
                    pickDateTime()
                }
            }
        } // 선택한 버튼의 id에 따라 다른 선택지

        // setOnClickListener 설정
        mood_sunny.setOnClickListener(clickListener)
        mood_cloudy.setOnClickListener(clickListener)
        mood_cloud.setOnClickListener(clickListener)
        mood_rainy.setOnClickListener(clickListener)
        mood_snowy.setOnClickListener(clickListener)
        dateBtn.setOnClickListener(clickListener)

        try {
            val bundle : Bundle = intent.extras!!
            id = bundle.getInt("ID", 0)
            if (id != 0) {
                edtTitle.setText(bundle.getString("title"))
                edtDesc.setText(bundle.getString("desc"))
            }
        } catch (ex:Exception) {

        }
    }

    // 작성 시간 설정
    private fun pickDateTime() {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        try{
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                tvDate.setText(SimpleDateFormat("yyyy-MM-dd HH:mm").format(pickedDateTime.time))
                }, startHour, startMinute, false).show()
            }, startYear, startMonth, startDay).show()
        } catch (ex:Exception) {
            Toast.makeText(this,"과정에서 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // 다이어리 추가 함수
    fun addFunc(view:View) {
        val dbManager = DiaryDBManager(this)
        val values = ContentValues() // 각종 데이터 저장
        values.put("Title", edtTitle.text.toString()) // 제목
        values.put("Description", edtDesc.text.toString()) // 내용
        values.put("Mood", selMood) // 마음 날씨
        values.put("Date", tvDate.text.toString()) // 작성 날짜

        if (id == 0) {
            val ID = dbManager.insert(values)
            // DBManager에 insert
            if (ID > 0) {
                Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "과정 중 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        } else {
            val selectionArgs = arrayOf(id.toString())
            val ID = dbManager.update(values, "ID=?", selectionArgs)
            if (ID > 0) {
                Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "과정 중 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}