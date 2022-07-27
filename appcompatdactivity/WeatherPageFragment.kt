package com.example.appcompatdactivity

import android.os.Bundle
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class WeatherPageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater
            .inflate(R.layout.weather_page_fragment,
                container, false)

        val statusText = view.findViewById<TextView>(R.id.weather_status_text)
        val temperatureText = view.findViewById<TextView>(R.id.weather_temp_text)
        val weatherImage = view.findViewById<ImageView>(R.id.weather_icon)

        statusText.text = arguments?.getString("status")
        temperatureText.text = arguments?.getDouble("temperature").toString()
        weatherImage.setImageResource(arguments?.getInt("res_id")!!)

        return view
    }
    companion object{
        fun  newInstance(status : String, temperature : Double) : WeatherPageFragment {
            val fragment = WeatherPageFragment()

            val args = Bundle()
            args.putString("status", status)
            args.putDouble("temperature", temperature)
            args.putInt("res_id", R.drawable.sun)
            fragment.arguments = args

            return fragment
        }
    }
}