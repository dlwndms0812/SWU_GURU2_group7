package com.example.guru_group7.note

import androidx.appcompat.app.AppCompatActivity
import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.guru_group7.R
import java.lang.Exception
import kotlinx.android.synthetic.main.activity_add_note.*


class AddNoteActivity:AppCompatActivity() {
    private var id = 0
    private var selMood = 0
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

        val clickListener = View.OnClickListener { view ->
            when (view.getId()) {
                R.id.mood_sunny -> selMood = 1
                R.id.mood_rainy -> selMood = 2
                R.id.mood_cloudy -> selMood = 3
                R.id.mood_snowy -> selMood = 4
                R.id.mood_rainbow -> selMood = 5
            }
        }

        mood_sunny.setOnClickListener(clickListener)
        mood_rainy.setOnClickListener(clickListener)
        mood_cloudy.setOnClickListener(clickListener)
        mood_snowy.setOnClickListener(clickListener)
        mood_rainbow.setOnClickListener(clickListener)

        try {
            val bundle : Bundle = intent.extras!!
            id = bundle.getInt("ID", 0)
            if (id != 0) {
                edtTitle.setText(bundle.getString("name"))
                edtDesc.setText(bundle.getString("desc"))

            }
        } catch (ex:Exception) {}
    }

    fun addFunc(view:View) {
        var dbManager = noteDBManager(this)
        var values = ContentValues()
        values.put("Title", edtTitle.text.toString())
        values.put("Description", edtDesc.text.toString())
        values.put("Mood", selMood)

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