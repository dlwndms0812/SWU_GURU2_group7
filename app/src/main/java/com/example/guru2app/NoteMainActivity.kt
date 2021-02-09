package com.example.guru2app

import android.app.SearchManager
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
// latient 설정X
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*
import kotlinx.android.synthetic.main.row.view.*

class NoteMainActivity : AppCompatActivity() {
    // 표시할 데이터(다이어리 내용)
    val listNotes = ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DB에서 읽어오기
        LoadQuery("%")
    }

    override fun onResume() {
        super.onResume()
        LoadQuery("%")
    }

    private fun LoadQuery(title: String) {
        var dbManager = noteDBManager(this)
        val projections = arrayOf("ID", "Title", "Description", "Mood")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.Query(projections, "Title like ?", selectionArgs, "Title")
        listNotes.clear()
        if (cursor.moveToFirst()) {

            do {
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))
                val Mood = cursor.getInt(cursor.getColumnIndex("Mood"))

                listNotes.add(Note(ID, Title, Description, Mood))

            } while (cursor.moveToNext())
        }

        //adapter
        var myNotesAdapter = MyNotesAdapter(this, listNotes)
        //set adapter
        lvNotes.adapter = myNotesAdapter

        //get total number of tasks from ListView
        val total = lvNotes.count
        //actionbar
        val mActionBar = supportActionBar
        if (mActionBar != null) {
            //actionbar 밑에 띄움
            mActionBar.subtitle = "총 $total 개의 메모가 있습니다."
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_menu_main, menu)

        //searchView
        val sv: SearchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView

        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                LoadQuery("%" + query + "%")
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                LoadQuery("%" + newText + "%")
                return false
            }
        });

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.addNote -> {
                    startActivity(Intent(this, AddNoteActivity::class.java))
                }
                R.id.action_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class MyNotesAdapter : BaseAdapter {
        var listNotesAdapter = ArrayList<Note>()
        var context: Context? = null

        constructor(context: Context, listNotesAdapter: ArrayList<Note>) : super() {
            this.listNotesAdapter = listNotesAdapter
            this.context = context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            //inflate layout row.xml
            var myView = layoutInflater.inflate(R.layout.row, null)
            val myNote = listNotesAdapter[position]
            myView.tvTitle.text = myNote.nodeName
            myView.tvDesc.text = myNote.nodeDes

            //삭제 버튼 클릭 시
            myView.deleteBtn.setOnClickListener {
                var dbManager = noteDBManager(this.context!!)
                val selectionArgs = arrayOf(myNote.nodeID.toString())
                dbManager.delete("ID=?", selectionArgs)
                LoadQuery("%")
            }
            //수정 버튼 클릭 시
            myView.editBtn.setOnClickListener {
                GoToUpdateFun(myNote)
            }
            //복사 버튼 클릭 시
            myView.copyBtn.setOnClickListener {
                //제목 가져오기
                val title = myView.tvTitle.text.toString()
                //내용 가져오기
                val desc = myView.tvDesc.text.toString()
                //제목과 내용 합치기
                val s = title + "\n" + desc
                val cb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cb.text = s // add to clipboard
                Toast.makeText(this@NoteMainActivity, "복사되었습니다.", Toast.LENGTH_SHORT).show()
            }
            //공유 버튼 클릭 시
            myView.shareBtn.setOnClickListener {
                //제목 가져오기
                val title = myView.tvTitle.text.toString()
                //내용 가져오기
                val desc = myView.tvDesc.text.toString()
                //제목과 내용 합치기(제목만 할지, 내용만 할지 선택)
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
            return listNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNotesAdapter.size
        }

    }

    private fun GoToUpdateFun(myNote: Note) {
        var intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra("ID", myNote.nodeID) //put id
        intent.putExtra("name", myNote.nodeName) //ut name
        intent.putExtra("desc", myNote.nodeDes) //put description
        startActivity(intent) //start activity
    }

}
