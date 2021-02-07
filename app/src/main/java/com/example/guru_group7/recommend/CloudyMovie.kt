package com.example.guru_group7.recommend

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.guru_group7.R

class CloudyMovie: AppCompatActivity() {
    override fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cloudy_movie)
        val items= mutableListOf<ListViewItem>()

        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.cloudy_hi)!!, "안녕, 헤이즐", "감독-조쉬 분  장르-드라마, 멜로/로맨스"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.cloudy_forest)!!, "포레스트 검프", "감독-로버트 저메키스  장르-드라마, 코미디"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.cloudy_eraser)!!, "내 머리 속의 지우개", "감독-이재한  장르-멜로/로맨스, 코미디, 드라마"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.cloudy_inter)!!, "인터스텔라", "감독-크리스토퍼 놀란  장르-SF"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.cloudy_king)!!, "킹스맨: 시크릿 에이전트", "감독-매튜 본  장르-액션, 스릴러"))

        val adapter: ListViewAdapter(items)
        listView.adpater=adapter

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()

        }
}