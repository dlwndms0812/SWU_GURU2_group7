package com.example.guru_group7.recommend

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.guru_group7.R


class SunnyCloudyMovie: AppCompatActivity() {
    override fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sunnycloudy_movie)
        val items= mutableListOf<ListViewItem>()

        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunnycloudy_kid)!!, "날씨의 아이", "감독-신카이 마코토  장르-애니메이션"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunnycloudy_little)!!, "리틀 포레스트", "감독-임순례  장르-드라마"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunnycloudy_tomorrow)!!, "나는 내일, 어제의 너와 만난다", "감독-미키 타카히로  장르-멜로/로맨스, 판타지 "))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunnycloudy_begin)!!, "비긴 어게인", "감독-존 카니  장르-드라마, 멜로/로맨스, 코미디"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.sunnycloudy_soul)!!, "소울", "감독-피트 닥터  장르-애니메이션"))

        val adapter: ListViewAdapter(items)
        listView.adpater=adapter

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
    }
}