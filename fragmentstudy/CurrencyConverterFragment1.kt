package com.example.fragmentstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

class CurrencyConverterFragment1 : Fragment() {

    val currencyExchangeMap = mapOf(
        "USD" to 1.0,
        "EUR" to 0.9,
        "JPT" to 110.0,
        "KRW" to 1150.0
    )

    fun calculateCurrency(amount : Double, from : String, to : String) : Double {
        var USDAmount = if(from != "USD") {
            (amount / currencyExchangeMap[from]!!)
        } else {
            amount
        }
        return currencyExchangeMap[to]!! * USDAmount
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.currency_converter_fragment1,
            container,
            false //무조건 false
        )

        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)
        val fromCurrencySpinner = view.findViewById<Spinner>(R.id.from_currency)
        val toCurrencySpinner = view.findViewById<Spinner>(R.id.to_Currency)

        val currencySelectionArrayAdapter = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_spinner_item,
            listOf("USD","EUR", "JPT", "KRW")
        )
        currencySelectionArrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        fromCurrencySpinner.adapter = currencySelectionArrayAdapter
        toCurrencySpinner.adapter = currencySelectionArrayAdapter

        val itemSelectrdListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
        fromCurrencySpinner.onItemSelectedListener = itemSelectrdListener
        toCurrencySpinner.onItemSelectedListener = itemSelectrdListener

        amount.addTextChangedListener{
            result.text = calculateCurrency(
                amount.text.toString().toDouble(),
                fromCurrencySpinner.selectedItem.toString(),
                toCurrencySpinner.selectedItem.toString()
            ).toString()


        }

        return view
    }
}