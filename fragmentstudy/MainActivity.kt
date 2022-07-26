package com.example.fragmentstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        /* transaction.add(R.id.fragment_container, CurrencyConverterFragment1())
        transaction.add(R.id.fragment_container, CurrencyConverterFragment2.newInstance("USD", "KRW"))
        transaction.commit()
        transaction.add(R.id.fragment_container, CurrencyConverterFragment2.newInstance("KRW", "USD"))
        transaction.commit() */
        transaction.add(R.id.fragment_container, CurrencyConverterFragment3.newInstance("KRW", "USD"))
        transaction.commit()
    }
}