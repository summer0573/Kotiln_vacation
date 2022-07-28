package com.example.appcompatdactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.weatherdustchecker.MyDeserialize
import com.example.weatherdustchecker.WeatherPageFragment
import java.text.FieldPosition

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
        val pagerApater = MypagerApater(supportFragmentManager, lat, lon)
        mPager.adapter = pagerApater

        mPager.addOnAdapterChangeListener(object : ViewPager.OnAdapterChangeListener {
            override fun onAdapterChanged(
                viewPager: ViewPager,
                oldAdapter: PagerAdapter?,
                newAdapter: PagerAdapter?
            ) {

            }

            override fun onPageScollsStateChange(statr: Int) {}
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    Toast.makeText(
                        applicationContext,
                        "날씨 페이지입니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (position == 1) {
                    Toast.makeText(
                        applicationContext,
                        "미세먼지 페이지입니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    class MypagerApater(fm : FragmentManager, val lat : Double, val lon : Double) : FragmentStatePagerAdapter(fm){
        override fun getCount() = 2

        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> WeatherPageFragment.newInstance(lat, lon)
                1 -> DustTestFragment.newInstance(lat, lon)
            }
        }


    }
}