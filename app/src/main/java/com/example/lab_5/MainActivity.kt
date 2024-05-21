package com.example.lab_5

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var buttonBmi: Button
    private lateinit var buttonToggleTheme: Button
    private lateinit var imageBmi: ImageView
    private lateinit var textBmi: TextView
    private lateinit var numberWeightLayout: TextInputLayout
    private lateinit var numberHeightLayout: TextInputLayout
    private lateinit var numberWeightEdit: TextInputEditText
    private lateinit var numberHeightEdit: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBmi = findViewById(R.id.buttonBmi)
        buttonToggleTheme = findViewById(R.id.buttonToggleTheme)
        imageBmi = findViewById(R.id.imageBmi)
        textBmi = findViewById(R.id.textBmi)
        numberWeightLayout = findViewById(R.id.numberWeight)
        numberHeightLayout = findViewById(R.id.numberHeight)
        numberWeightEdit = findViewById(R.id.numberWeightEditText)
        numberHeightEdit = findViewById(R.id.numberHeightEditText)

        buttonBmi.setOnClickListener {
            val weightInput = numberWeightEdit.text.toString()
            val heightInput = numberHeightEdit.text.toString()

            if (weightInput.isNotEmpty() && heightInput.isNotEmpty()) {
                try {
                    val weight = weightInput.toFloat()
                    val height = heightInput.toFloat() / 100 // convert to meters
                    val bmi = weight / (height * height)

                    Toast.makeText(this, "BMI $bmi", Toast.LENGTH_SHORT).show()

                    calculateBmi(bmi)

                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
                }
                imageBmi.visibility = ImageView.VISIBLE
            } else {
                Toast.makeText(this, "Please enter weight and height", Toast.LENGTH_SHORT).show()
            }
        }

        buttonToggleTheme.setOnClickListener {
            val currentNightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
            if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
    private fun calculateBmi(bmi: Float) {
        when {
            bmi < 16 -> {
                imageBmi.setImageResource(R.drawable.bmi_less16)
                textBmi.text = "Severe Thinness"
            }
            bmi in 16.0..16.99 -> {
                imageBmi.setImageResource(R.drawable.bmi_16_17)
                textBmi.text = "Moderate Thinness"
            }
            bmi in 17.0..18.49 -> {
                imageBmi.setImageResource(R.drawable.bmi_17_185)
                textBmi.text = "Mild Thinness"
            }
            bmi in 18.5..24.99 -> {
                imageBmi.setImageResource(R.drawable.bmi_185_25)
                textBmi.text = "Normal"
            }
            bmi in 25.0..29.99 -> {
                imageBmi.setImageResource(R.drawable.bmi_25_30)
                textBmi.text = "Overweight"
            }
            bmi in 30.0..34.99 -> {
                imageBmi.setImageResource(R.drawable.bmi_30_35)
                textBmi.text = "Obese Class I"
            }
            bmi in 35.0..39.99 -> {
                imageBmi.setImageResource(R.drawable.bmi_35_40)
                textBmi.text = "Obese Class II"
            }
            else -> {
                imageBmi.setImageResource(R.drawable.bmi_40more)
                textBmi.text = "Obese Class III"
            }
        }
    }
}
