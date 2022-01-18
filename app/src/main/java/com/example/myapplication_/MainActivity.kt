package com.example.myapplication_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val num = findViewById<EditText>(R.id.edit_text)
        val res = findViewById<TextView>(R.id.text_view)
        val slider = findViewById<Slider>(R.id.slider)
        val pplnum = findViewById<EditText>(R.id.ppl_num)
        var x = 0.0
        var p = 0
        var r = 0.0
        var ppl = 1
        var r2 = 0.0

        num.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                x = s.toString().toDouble()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (!s.isNullOrEmpty()) res.text = "Bill value: ${s.toString()}, tip percentage: $p%"
                if (!s.isNullOrEmpty()) res.text =
                    "Tip amount: ${String.format("%.2f", (s.toString().toDouble() * p / 100))}\n" +
                            "Everyone should pay ${r / ppl}"
                else res.text = ""
            }

        })

        slider.addOnChangeListener(object : Slider.OnChangeListener {
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                Log.d("addOnChangeListener", slider.value.toString())
                p = slider.value.toInt()
//                if (x != "") res.text = "Bill value: $x, tip percentage: $p%"
                r = x * p / 100
                if (x != 0.0) res.text = "Tip amount: ${String.format("%.2f", r)}\n" +
                        "Everyone should pay ${r / ppl}"
            }
        })
        pplnum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                ppl = s.toString().toInt()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (!s.isNullOrEmpty()) res.text = "Bill value: ${s.toString()}, tip percentage: $p%"
                if (x != null) res.text = "Tip amount: ${String.format("%.2f", r)}\n" +
                        "Everyone should pay ${r / s.toString().toInt()}"

            }

        })

    }
}
