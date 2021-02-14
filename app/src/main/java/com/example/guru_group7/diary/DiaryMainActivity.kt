package com.example.guru_group7.diary

import android.app.SearchManager
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.guru_group7.R
import kotlinx.android.synthetic.main.activity_add_diary.*

// xml에서 각종 정보를 받아옴
import kotlinx.android.synthetic.main.activity_diary_main.*
import kotlinx.android.synthetic.main.activity_diary_row.*
import kotlinx.android.synthetic.main.activity_diary_row.view.*

// 마음 다이어리 메인 부분
class DiaryMainActivity : AppCompatActivity() {
    // 표시할 데이터(다이어리 내용)
    val listDiaries = ArrayList<Diary>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_main)

        LoadQuery("%")

        // 다이어리 추가하기 버튼
        addBtn.setOnClickListener {
            startActivity(Intent(this, AddDiaryActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        LoadQuery("%")
    }

    // DB에서 다이어리 정보 읽어오기
    private fun LoadQuery(title: String) {
        var dbManager = DiaryDBManager(this)
        val projections = arrayOf("ID", "Title", "Description", "Mood", "Date")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.Query(projections, "Title like ?", selectionArgs, "Title")
        listDiaries.clear()
        if (cursor.moveToFirst()) {
            do {
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))
                val Mood = cursor.getInt(cursor.getColumnIndex("Mood"))
                val Date = cursor.getString(cursor.getColumnIndex("Date"))
                listDiaries.add(Diary(ID, Title, Description, Mood, Date))
            } while (cursor.moveToNext())
            // 순차적으로 읽어오기
        }

        // adapter 세팅
        var myDiaryAdapter = MyDiaryAdapter(this, listDiaries)
        lvDiaries.adapter = myDiaryAdapter
        // ListView의 task 총 개수를 가져옴
        val total = lvDiaries.count
        // 작성된 다이어리의 총 개수를 알려줌
        countDiary.text = "총 $total 개의 기록이 있습니다."
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_sub, menu)

        // 검색하기 기능 - SearchView (검색어 입력/검색 이벤트 처리)
        val searchView: SearchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // 검색 버튼 클릭
            override fun onQueryTextSubmit(query: String?): Boolean {
                LoadQuery("%" + query + "%")
                return false
            }
            // 입력하고 있는 단어 처리
            override fun onQueryTextChange(newText: String?): Boolean {
                LoadQuery("%" + newText + "%")
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    inner class MyDiaryAdapter : BaseAdapter {
        var listDiariesAdapter = ArrayList<Diary>()
        var context: Context? = null

        constructor(context: Context, listDiariesAdapter: ArrayList<Diary>) : super() {
            this.listDiariesAdapter = listDiariesAdapter
            this.context = context
        }

        // 다이어리 데이터 표시 방법 정의
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            // inflate activity_diary_main.xml
            var myView = layoutInflater.inflate(R.layout.activity_diary_row, null)
            val myDiary = listDiariesAdapter[position]
            myView.tvTitle.text = myDiary.nodeTitle
            myView.tvDesc.text = myDiary.nodeDes
            var selMood = myDiary.nodeMood
            when {
                selMood == 1 -> myView.ivMood.setImageResource(R.drawable.ic_diary_sun)
                selMood == 2 -> myView.ivMood.setImageResource(R.drawable.ic_diary_cloudy)
                selMood == 3 -> myView.ivMood.setImageResource(R.drawable.ic_diary_cloud)
                selMood == 4 -> myView.ivMood.setImageResource(R.drawable.ic_diary_rainy)
                selMood == 5 -> myView.ivMood.setImageResource(R.drawable.ic_diary_snowy)
                else -> myView.ivMood.setImageResource(R.drawable.ic_diary_question)
            }
            myView.tvDatetime.text = myDiary.nodeDate

            //삭제 버튼 클릭 시
            myView.deleteBtn.setOnClickListener {
                var dbManager = DiaryDBManager(this.context!!)
                val selectionArgs = arrayOf(myDiary.nodeID.toString())
                dbManager.delete("ID=?", selectionArgs)
                LoadQuery("%")
                Toast.makeText(this@DiaryMainActivity, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }

            //수정 버튼 클릭 시
            myView.editBtn.setOnClickListener {
                GoToUpdateFun(myDiary)
            }

            //복사 버튼 클릭 시
            myView.copyBtn.setOnClickListener {
                //제목 가져오기
                val title = myView.tvTitle.text.toString()
                //내용 가져오기
                val desc = myView.tvDesc.text.toString()
                //제목과 내용 합치기
                val s = title + "\n" + desc
                val clibBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clibBoard.text = s // 클립보드에 추가
                Toast.makeText(this@DiaryMainActivity, "복사되었습니다.", Toast.LENGTH_SHORT).show()
            }

            //공유 버튼 클릭 시
            myView.shareBtn.setOnClickListener {
                //제목 가져오기
                val title = myView.tvTitle.text.toString()
                //내용 가져오기
                val desc = myView.tvDesc.text.toString()
                //제목과 내용 합치기
                val s = title + "\n" + desc
                //공유하기
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, s)
                startActivity(Intent.createChooser(shareIntent, s))
            }

            return myView

        }

        override fun getItem(position: Int): Any {
            return listDiariesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listDiariesAdapter.size
        }
    }

    private fun GoToUpdateFun(myDiary: Diary) {
        var intent = Intent(this, AddDiaryActivity::class.java)
        // id, title, desc, mood, date 데이터를 전달
        intent.putExtra("ID", myDiary.nodeID)
        intent.putExtra("title", myDiary.nodeTitle)
        intent.putExtra("desc", myDiary.nodeDes)
        intent.putExtra("mood", myDiary.nodeMood)
        intent.putExtra("date", myDiary.nodeDate)
        startActivity(intent) //activity 실행
    }
}
