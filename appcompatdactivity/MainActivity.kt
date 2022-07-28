package com.example.appcompatdactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.weatherdustchecker.MyDeserialize
import com.example.weatherdustchecker.WeatherPageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mPager : ViewPager
    private var lat : Double = 37.579876
    private var lon : Double = 126.976998


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //상단 제목 표시줄 숨기기
        supportActionBar?.hide()

        mPager = findViewById(R.id.pager)
        val pagerApater = MypagerApater(supportFragmentManager)
        mPager.adapter = pagerApater

//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.fragment_container,
//            DustTestFragment.newInstance(37.58,126.98))
//        transaction.commit()


    }
}