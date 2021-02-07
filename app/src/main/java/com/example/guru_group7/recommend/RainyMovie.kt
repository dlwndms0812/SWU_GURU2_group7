package com.example.guru_group7.recommend

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.guru_group7.R

class RainyMovie: AppCompatActivity() {
    override fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rainy_movie)
        val items= mutableListOf<ListViewItem>()

        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.rain_meet)!!, "지금, 만나러 갑니다", "감독-도이 노부히로   장르-멜로/로맨스, 드라마, 판타지"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.rain_notebook)!!, "노트북", "감독-닉 카사베츠   장르-멜로/로맨스, 드라마"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.rain_classic)!!, "클래식", "감독-곽재용  장르-멜로/로맨스, 드라마"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.rain_memory)!!, "살인의 추억", "감독-봉준호  장르-범죄, 미스터리, 스릴러, 코미디, 드라마"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.rain_conjuring)!!, "컨저링", "감독-제임스 완   장르-공포 스릴러"))

        val adapter: ListViewAdapter(items)
        listView.adpater=adapter

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
    }
}