package com.example.guru_group7.recommend

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.guru_group7.R

class SnowMovie: AppCompatActivity() {
    override fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snow_movie)
        val items= mutableListOf<ListViewItem>()

        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.snow_etener)!!, "이터널 션샤인", "감독-미셸 공드리  장르-멜로/로맨스, 드라마, SF"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.snow_frozen)!!, "겨울왕국", "감독-크리스 벅, 제니퍼 리  장르-애니메이션, 모험, 코미디, 가족, 판타지, 뮤지컬"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.snow_loveletter)!!, "러브레터", "감독-이와이 슌지   장르-드라마, 멜로/로맨스"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.snow_train)!!, "설국열차", "감독-봉준호  장르-SF,액션,드라마"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.snow_love)!!, "러브 액츄얼리", "감독-리차드 커티스    장르-멜로/로맨스, 드라마, 코미디"))

        val adapter: ListViewAdapter(items)
        listView.adpater=adapter

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
    }
}