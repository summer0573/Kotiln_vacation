package com.example.weatherdustchecker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appcompatdactivity.R
import org.w3c.dom.Text
import wikibook.learnandroid.weatherdustchecker.APICall
import java.net.URL

class WeatherPageFragment : Fragment() {
    lateinit var weatherImage : ImageView
    lateinit var statusText : TextView
    lateinit var temperatureText : TextView

    var APP_ID = "66df494d38dec916a286ac31d4cb758f"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater
            .inflate(
                R.layout.weather_page_fragment,
                container, false)

//      ToDO: arguments 값 참조해서 두개 값 가져오고, 해당하는 뷰에 출력해주기

        statusText = view.findViewById<TextView>(R.id.weather_status_text)
        temperatureText = view.findViewById<TextView>(R.id.weather_temp_text)
        weatherImage = view.findViewById<ImageView>(R.id.weather_icon)

//        weatherImage.setImageResource(arguments?.getInt("res_id")!!)
//          Todo : ImagaViw 가져와서 sun 이미지 출력하기
//        status.text = arguments?.getString("status")
//        temperature.text = arguments?.getDouble("temperature").toString()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lat = arguments?.getDouble("lat")
        val lon = arguments?.getDouble("lon")

        val url = "https://api.openweathermap.org/data/2.5/weather?units=metric&appid=${APP_ID}&lat=${lat}&lon=${lon}"

        APICall(object : APICall.APICallback{
            override fun onComplete(result: String) {
                Log.d("mytag", result)
            }
        }).execute(URL(url))
    }

    companion object{
        fun newInstance(lat: Double, lon: Double)
                : WeatherPageFragment{

            val fragment = WeatherPageFragment()

            val args = Bundle()
            args.putDouble("lat", lat)
            args.putDouble("lon", lon)
            fragment.arguments = args

            return fragment
        }
    }
}