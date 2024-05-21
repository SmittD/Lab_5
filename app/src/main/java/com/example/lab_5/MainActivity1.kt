package com.example.lab_5

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.pow

class MainActivity1 : AppCompatActivity() {
    private lateinit var buttonCount: Button
    private lateinit var editTextPensjaBrutto: TextInputEditText
    private lateinit var editTextProcentPracownika: TextInputEditText
    private lateinit var editTextProcentPracodawcy: TextInputEditText
    private lateinit var editTextLatDoEmerytury: TextInputEditText
    private lateinit var editTextDoplaty: TextInputEditText
    private lateinit var editTextPoczatkowyKapital: TextInputEditText
    private lateinit var editTextRocznaStopaZwrotu: TextInputEditText
    private lateinit var editTextWynik: TextInputEditText

    private var wynik: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        buttonCount = findViewById(R.id.buttonCount)
        editTextPensjaBrutto = findViewById(R.id.editTextPensjaBrutto)
        editTextProcentPracownika = findViewById(R.id.editTextProcentPracownika)
        editTextProcentPracodawcy = findViewById(R.id.editTextProcentPracodawcy)
        editTextLatDoEmerytury = findViewById(R.id.editTextLatDoEmerytury)
        editTextDoplaty = findViewById(R.id.editTextDoplaty)
        editTextPoczatkowyKapital = findViewById(R.id.editTextPoczatkowyKapital)
        editTextRocznaStopaZwrotu = findViewById(R.id.editTextRocznaStopaZwrotu)
        editTextWynik = findViewById(R.id.editTextWynik)

        buttonCount.setOnClickListener {
            count(
                editTextPensjaBrutto,
                editTextProcentPracownika,
                editTextProcentPracodawcy,
                editTextLatDoEmerytury,
                editTextDoplaty,
                editTextPoczatkowyKapital,
                editTextRocznaStopaZwrotu
            )
            editTextWynik.setText(wynik.toString())
        }
    }

    private fun count(
        PensjaBrutto: TextInputEditText,
        ProcentPracownika: TextInputEditText,
        ProcentPracodawcy: TextInputEditText,
        LatDoEmerytury: TextInputEditText,
        Doplaty: TextInputEditText,
        PoczatkowyKapital: TextInputEditText,
        RocznaStopaZwrotu: TextInputEditText
    ) {
        if (PensjaBrutto.text.toString().isNotEmpty()
            && ProcentPracownika.text.toString().isNotEmpty()
            && ProcentPracodawcy.text.toString().isNotEmpty()
            && LatDoEmerytury.text.toString().isNotEmpty()
            && Doplaty.text.toString().isNotEmpty()
            && PoczatkowyKapital.text.toString().isNotEmpty()
            && RocznaStopaZwrotu.text.toString().isNotEmpty()
        ) {
            val pensjaBrutto = PensjaBrutto.text.toString().toDouble()
            val procentPracownika = ProcentPracownika.text.toString().toDouble()
            val procentPracodawcy = ProcentPracodawcy.text.toString().toDouble()
            val latDoEmerytury = LatDoEmerytury.text.toString().toInt()
            val doplaty = Doplaty.text.toString().toDouble()
            val poczatkowyKapital = PoczatkowyKapital.text.toString().toDouble()
            val rocznaStopaZwrotu = RocznaStopaZwrotu.text.toString().toDouble() / 100

            val rocznaSkladka = (pensjaBrutto * (procentPracownika + procentPracodawcy) / 100) * 12 + doplaty
            if (rocznaStopaZwrotu != 0.0){
                var przyszlaWartoscSkladek = 0.0
                for (i in 1..latDoEmerytury) {
                    przyszlaWartoscSkladek = (przyszlaWartoscSkladek + rocznaSkladka) * (1 + rocznaStopaZwrotu)
                }

                val przyszlaWartoscKapitalu = poczatkowyKapital * (1 + rocznaStopaZwrotu).pow(
                    latDoEmerytury.toDouble()
                )

                wynik = przyszlaWartoscSkladek + przyszlaWartoscKapitalu
            } else {
                wynik = rocznaSkladka * latDoEmerytury
            }


            Toast.makeText(applicationContext, "Wynik obliczony: $wynik", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Wpisz wszystkie dane", Toast.LENGTH_SHORT).show()
        }
    }
}
