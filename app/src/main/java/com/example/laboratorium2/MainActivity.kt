package com.example.laboratorium2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalkulator()
        }
    }
}

data class Jednostka(val nazwa: String, val mnoznik: Double)


fun przeliczIFormatuj(wartosc: Double, mnoznik: Double): String {
    val obliczonyWynik = wartosc * mnoznik
    return if (obliczonyWynik >= 1000.0 || (obliczonyWynik < 0.001 && obliczonyWynik > 0.0)) {
        String.format(java.util.Locale.US, "%.4e", obliczonyWynik)
    } else {
        String.format(java.util.Locale.US, "%.4f", obliczonyWynik)
    }
}

@Composable
fun Kalkulator() {
    var wpisana by remember { mutableStateOf("") }
    val liczba = wpisana.toDoubleOrNull()


    val listaJednostek = listOf(
        Jednostka("Centymetry", 100.0),
        Jednostka("Kilometry", 0.001),
        Jednostka("Mile", 0.000621371),
        Jednostka("Lata świetlne", 1.057e-16)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = wpisana,
            onValueChange = { wpisana = it },
            label = { Text("Wpisz wartość (w metrach)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        if (liczba != null) {
            Text(
                text = "Wyniki przeliczeń:",
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                fontWeight = FontWeight.Bold
            )

            listaJednostek.forEach { jednostka ->
                val sformatowanyWynik = przeliczIFormatuj(liczba, jednostka.mnoznik)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = jednostka.nazwa, fontWeight = FontWeight.Bold)
                        Text(text = sformatowanyWynik)
                    }
                }
            }
        } else {
            Text(
                text = "Wpisz poprawną liczbę, aby zobaczyć tabele wyników.",
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}