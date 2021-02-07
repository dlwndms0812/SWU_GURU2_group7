package com.example.guru_group7.recommend

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.guru_group7.R

class SunnyMovie:AppCompatActivity() {
    override fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sunny_movie)
        val items= mutableListOf<ListViewItem>()

        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunny_kiki)!!, "마녀 배달부 키키", "감독-미야자키 하야오  장르-애니메이션, 모험, 판타지, 가족"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunny_mammamia)!!, "맘마미아", "감독-필리다 로이드  장르-코미디, 뮤지컬, 멜로/로맨스"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunny_500day)!!, "500일의 섬머", "감독-마크 웹  장르-코미디, 드라마, 멜로/로맨스"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunny_lala)!!, "라라랜드", "감독-데이미언 셔젤  장르-드라마, 뮤지컬, 멜로/로맨스"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunny_susang)!!, "수상한그녀", "감독-황동혁  장르-코미디, 드라마"))

        val adapter: ListViewAdapter(items)
        listView.adpater=adapter

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()


    }
}