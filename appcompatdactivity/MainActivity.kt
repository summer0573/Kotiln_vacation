package com.example.appcompatdactivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.weatherdustchecker.WeatherPageFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var mPager : ViewPager
    private var lat : Double = 37.579876
    private var lon : Double = 126.976998

    lateinit var locationManager: LocationManager
    lateinit var locationListner: LocationListener
    val PERMISSIN_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationListner = LocationListener {
            lat = it.latitude
            lon = it.longitude
            Log.d("mytag",lat.toString())
            Log.d("mytag",lon.toString())
            locationManager.removeUpdates(locationListner)

            val pagerApater = MyPageAdapter(supportFragmentManager, lat, lon)
            mPager.adapter = pagerApater

        }

        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    ==PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                locationListner
            )
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                PERMISSIN_REQUEST_CODE)
        }

        //상단 제목 표시줄 숨기기
        supportActionBar?.hide()

        mPager = findViewById(R.id.pager)

        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,              //0부터
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                if(position == 0){
                    Toast.makeText(applicationContext,
                        "날씨 페이지 입니다",
                        Toast.LENGTH_SHORT).show()
                }else if(position == 1){
                    Toast.makeText(applicationContext,
                        "미세먼지 페이지 입니다.",
                        Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSIN_REQUEST_CODE){
            var allPermissionGranted = true
            for(result in grantResults) {
                allPermissionGranted = (result == PackageManager.PERMISSION_GRANTED)
                if(!allPermissionGranted) break
            }
            if(allPermissionGranted){
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0, 0f, locationListner)
            }else {
                Toast.makeText(applicationContext,
                    "위치 정보 제공 동의가 필요합니다.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }

    //  inner 클래스는 내부 클래스는 안에 있는 외부클래스의 속성에 접근이 가능하다!
    class MyPageAdapter(frm: FragmentManager, val lat: Double, val lon: Double) :
        FragmentStatePagerAdapter(frm) {
        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> WeatherPageFragment.newInstance(lat, lon)
                1 -> DustTestFragment.newInstance(lat, lon)
                else -> throw Exception("페이지가 존재하지 않습니다")
            }
        }

    }

}