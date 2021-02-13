package com.example.guru_group7.note

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import com.example.guru_group7.R
import java.lang.Exception
import kotlinx.android.synthetic.main.activity_add_note.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class AddNoteActivity : AppCompatActivity() {
    private var id = 0
    private var selMood = 0

    lateinit var mood_sunny: AppCompatImageButton
    lateinit var mood_rainy: AppCompatImageButton
    lateinit var mood_cloudy: AppCompatImageButton
    lateinit var mood_snowy: AppCompatImageButton
    lateinit var mood_rainbow: AppCompatImageButton

    lateinit var tvDate: TextView
    lateinit var dateBtn: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        mood_sunny = findViewById<AppCompatImageButton>(R.id.mood_sunny)
        mood_rainy = findViewById<AppCompatImageButton>(R.id.mood_rainy)
        mood_cloudy = findViewById<AppCompatImageButton>(R.id.mood_cloudy)
        mood_snowy = findViewById<AppCompatImageButton>(R.id.mood_snowy)
        mood_rainbow = findViewById<AppCompatImageButton>(R.id.mood_rainbow)
        tvDate = findViewById<TextView>(R.id.tvDate)
        dateBtn = findViewById<AppCompatButton>(R.id.button)

        val current = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

        tvDate.setText(current) // 시간 기본 설정

        val clickListener = View.OnClickListener { view ->
            when (view.getId()) {
                R.id.mood_sunny -> selMood = 1
                R.id.mood_rainy -> selMood = 2
                R.id.mood_cloudy -> selMood = 3
                R.id.mood_snowy -> selMood = 4
                R.id.mood_rainbow -> selMood = 5
                R.id.button -> {
                    pickDateTime()
                }
            }
        }

        mood_sunny.setOnClickListener(clickListener)
        mood_rainy.setOnClickListener(clickListener)
        mood_cloudy.setOnClickListener(clickListener)
        mood_snowy.setOnClickListener(clickListener)
        mood_rainbow.setOnClickListener(clickListener)
        dateBtn.setOnClickListener(clickListener)

        try {
            val bundle : Bundle = intent.extras!!
            id = bundle.getInt("ID", 0)
            if (id != 0) {
                edtTitle.setText(bundle.getString("name"))
                edtDesc.setText(bundle.getString("desc"))
            }
        } catch (ex:Exception) {
        }
    }

    // 시간 설정
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

        }
    }


    fun addFunc(view:View) {
        var dbManager = NoteDBManager(this)
        var values = ContentValues()
        values.put("Title", edtTitle.text.toString())
        values.put("Description", edtDesc.text.toString())
        values.put("Mood", selMood)
        values.put("Date", tvDate.text.toString())

        if (id == 0) {
            val ID = dbManager.insert(values)
            if (ID > 0) {
                Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "과정 중 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        } else {
            var selectionArgs = arrayOf(id.toString())
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
