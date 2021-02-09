package com.example.guru2app

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import kotlinx.android.synthetic.main.activity_add_note.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddNoteActivity : AppCompatActivity() {
    private var id = 0
    private var selEmotion = 0
    //val date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    lateinit var mood_sunny: Button
    lateinit var mood_rainy: Button
    lateinit var mood_cloudy: Button
    lateinit var mood_snowy: Button
    lateinit var mood_rainbow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        mood_sunny = findViewById<Button>(R.id.mood_sunny)
        mood_rainy = findViewById<Button>(R.id.mood_rainy)
        mood_cloudy = findViewById<Button>(R.id.mood_cloudy)
        mood_snowy = findViewById<Button>(R.id.mood_snowy)
        mood_rainbow = findViewById<Button>(R.id.mood_rainbow)

        try {
            val bundle : Bundle = intent.extras!!
            id = bundle.getInt("ID", 0)
            if (id != 0) {
                edtTitle.setText(bundle.getString("name"))
                edtDesc.setText(bundle.getString("desc"))

            }
        } catch (ex:Exception) {}
    }

    fun onClick(view: View?){
        when (view?.id) {
            R.id.mood_sunny -> selEmotion = 1
            R.id.mood_rainy -> selEmotion = 2
            R.id.mood_cloudy -> selEmotion = 3
            R.id.mood_snowy -> selEmotion = 4
            R.id.mood_rainbow -> selEmotion = 5
            else -> selEmotion = 5
        }
    }

    fun addFunc(view:View) {
        var dbManager = noteDBManager(this)
        var values = ContentValues()
        values.put("Title", edtTitle.text.toString())
        values.put("Description", edtDesc.text.toString())
        values.put("Mood", selEmotion)

        if (id == 0) {
            val ID = dbManager.insert(values)
            if (ID > 0) {
                Toast.makeText(this, "노트가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "과정 중 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        } else {
            var selectionArgs = arrayOf(id.toString())
            val ID = dbManager.update(values, "ID=?", selectionArgs)
            if (ID > 0) {
                Toast.makeText(this, "노트가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "과정 중 에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}