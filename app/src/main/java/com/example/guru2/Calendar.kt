package com.example.test

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.*
import com.example.guru2.R

class Calendar : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var calendarView : CalendarView
    lateinit var dateTextView: TextView
    lateinit var diaryEditText: EditText
    lateinit var textView2 : TextView
    lateinit var btnSave : Button
    lateinit var btnEdit : Button
    lateinit var btnDel : Button

    lateinit var diaryContent : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar)


        calendarView = findViewById(R.id.calendarView)
        dateTextView = findViewById(R.id.dateTextView)
        diaryEditText = findViewById(R.id.diaryEditText)
        textView2 = findViewById(R.id.textView2)
        btnSave = findViewById<Button>(R.id.btnSave)
        btnEdit = findViewById<Button>(R.id.btnEdit)
        btnDel = findViewById<Button>(R.id.btnDel)

        dbManager = DBManager(this, "diaryDB", null, 1)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->  // 달력 날짜가 선택되면

            dateTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)  // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            diaryEditText.setText("") // EditText에 공백값 넣기
            textView2.text = ""
            sqlitedb = dbManager.readableDatabase

            var cursor: Cursor
            cursor = sqlitedb.rawQuery("SELECT * FROM diaryTBL WHERE date = '" + dateTextView.text + "';", null)

            if(cursor.moveToNext()){
                diaryContent = cursor.getString(cursor.getColumnIndex("diary")).toString()
                textView2.text = diaryContent
            }


            if (textView2.text == "") {

                dateTextView.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
                btnSave.visibility = View.VISIBLE // 저장 버튼이 Visible
                diaryEditText.visibility = View.VISIBLE // EditText가 Visible
                textView2.visibility = View.INVISIBLE // 저장된 일기 textView가 Invisible
                btnEdit.visibility = View.INVISIBLE // 수정 Button이 Invisible
                btnDel.visibility = View.INVISIBLE // 삭제 Button이 Invisible
            } else {
                dateTextView.visibility = View.VISIBLE
                btnSave.visibility = View.INVISIBLE
                diaryEditText.visibility = View.INVISIBLE
                textView2.visibility = View.VISIBLE
                btnEdit.visibility = View.VISIBLE
                btnDel.visibility = View.VISIBLE
            }

            cursor.close()
            sqlitedb.close()
            dbManager.close()



            btnSave.setOnClickListener { // 저장 Button이 클릭되면
                Toast.makeText(this, "일기를 저장했습니다.", Toast.LENGTH_SHORT).show()
                var str_diary: String = diaryEditText.text.toString() // str 변수에 일기 내용을 toString 형으로 저장
                var str_date: String = dateTextView.text.toString() // str 변수에 날짜를 toString 형으로 저장

                textView2.text = "$str_diary" // textView2에 str 출력
                btnSave.visibility = View.INVISIBLE
                btnEdit.visibility = View.VISIBLE
                btnDel.visibility = View.VISIBLE
                diaryEditText.visibility = View.INVISIBLE
                textView2.visibility = View.VISIBLE

                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO diaryTBL VALUES ('" + str_diary + "', '" + str_date + "')")
                sqlitedb.close()
            }

            btnEdit.setOnClickListener {
                var str_diary: String = textView2.text.toString() // str 변수에 일기 내용을 toString 형으로 저장

                diaryEditText.setText(str_diary)
                btnSave.visibility = View.VISIBLE
                btnEdit.visibility = View.INVISIBLE
                btnDel.visibility = View.INVISIBLE
                diaryEditText.visibility = View.VISIBLE
                textView2.visibility = View.INVISIBLE

            }

            btnDel.setOnClickListener {

                var str_date: String = dateTextView.text.toString() // str 변수에 날짜를 toString 형으로 저장

                diaryEditText.setText("")
                Toast.makeText(this, "일기를 삭제했습니다.", Toast.LENGTH_SHORT).show()
                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("DELETE FROM diaryTBL WHERE date='"+ str_date + "';")
                sqlitedb.close()



                btnSave.visibility = View.VISIBLE
                btnEdit.visibility = View.INVISIBLE
                btnDel.visibility = View.INVISIBLE
                diaryEditText.visibility = View.VISIBLE
                textView2.visibility = View.INVISIBLE
            }
        }
    }
}
