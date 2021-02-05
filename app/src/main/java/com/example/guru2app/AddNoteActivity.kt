package com.example.guru2app

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        try {
            val bundle:Bundle = intent.extras!!
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