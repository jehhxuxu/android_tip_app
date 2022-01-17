package com.example.android_tip_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.android_tip_app.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Calculate Tip"

        binding.calculateButton.setOnClickListener {
            it.hideKeyboard()
            calculateTip()
        }
    }

    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun calculateTip() {

        val serviceText = binding.costOfServiceEditText.text.toString()
        if (serviceText.isNotEmpty()) {
            val serviceValue = serviceText.toDouble()
            val tip = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.option_twenty -> 0.20
                R.id.option_eighteen -> 0.18
                else -> 0.15
            }
            var result = (serviceValue * tip)
            if (binding.roundSwitch.isChecked) {
                result = kotlin.math.ceil(result)
            }
            val formattedText = NumberFormat.getCurrencyInstance().format(result)
            binding.result.text = getString(R.string.tip_amount,formattedText)
        } else {
            binding.result.text = ""
            Snackbar.make(
                this,
                binding.constraintLayout,
                "Input cost of service",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}

