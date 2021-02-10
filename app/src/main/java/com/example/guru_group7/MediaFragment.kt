package com.example.guru_group7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MediaFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 날씨 추천 페이지...어떻게 바꿀까
        return inflater.inflate(R.layout.sunny, container, false)
    }

}