package com.example.bmi_calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmi_calculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.calculate.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val height = binding.height.text.toString().toFloatOrNull()
        val weight = binding.weight.text.toString().toFloatOrNull()

        if (height != null && weight != null && height > 0 && weight > 0) {
            val bmi = weight / (height / 100).pow(2)
            val bmiCategory = when {
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Normal Weight"
                bmi < 30 -> "Overweight"
                else -> "Obese"
            }
            val bmiResult = String.format("%.2f", bmi)
            binding.result.text = "BMI: $bmiResult\nCategory: $bmiCategory"
        } else {
            binding.result.text = "Invalid Input"
        }
    }
}
