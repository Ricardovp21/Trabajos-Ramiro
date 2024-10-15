package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var pantalla: TextView
    private var numeroActual: String = ""
    private var primerNumero: Double? = null
    private var esNuevaOperacion: Boolean = true
    private var operacionActual: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantalla = findViewById(R.id.pantalla)

        // Configurar los botones numéricos y los operadores
        configurarBotonesNumericos()
        configurarBotonesOperadores()
    }

    // botones (numeros)
    private fun configurarBotonesNumericos() {
        val idsBotonesNumericos = intArrayOf(
            R.id.btnCero, R.id.btnUno, R.id.btnDos, R.id.btnTres,
            R.id.btnCuatro, R.id.btnCinco, R.id.btnSeis, R.id.btnSiete,
            R.id.btnOcho, R.id.btnNueve, R.id.btnPunto
        )

        val listener = View.OnClickListener { v ->
            val boton = v as Button
            if (esNuevaOperacion) {
                numeroActual = ""
                esNuevaOperacion = false
            }
            numeroActual += boton.text.toString()
            pantalla.text = numeroActual
        }

        for (id in idsBotonesNumericos) {
            findViewById<View>(id).setOnClickListener(listener)
        }
    }

    // Configurar operadores (botones)
    private fun configurarBotonesOperadores() {
        findViewById<View>(R.id.btnSumar).setOnClickListener {
            seleccionarOperacion(suma())
        }

        findViewById<View>(R.id.btnRestar).setOnClickListener {
            seleccionarOperacion(resta())
        }

        findViewById<View>(R.id.btnMultiplicar).setOnClickListener {
            seleccionarOperacion(multiplicacion())
        }

        findViewById<View>(R.id.btnDividir).setOnClickListener {
            seleccionarOperacion(division())
        }

        // boton igual
        findViewById<View>(R.id.btnIgual).setOnClickListener { calcularResultado() }

        // boton limppiar
        findViewById<View>(R.id.btnLimpiar).setOnClickListener { reiniciarCalculadora() }
    }

    // Método para seleccionar la operación
    private fun seleccionarOperacion(operacion: Any) {
        if (numeroActual.isNotEmpty()) {
            primerNumero = numeroActual.toDouble()
            operacionActual = operacion
            esNuevaOperacion = true
        }
    }

    // calcular resultado
    private fun calcularResultado() {
        if (primerNumero == null || numeroActual.isEmpty()) {
            pantalla.text = "Error, revise la operación"
            return
        }

        val segundoNumero = numeroActual.toDouble()
        val resultado: Double?

        try {
            resultado = when (operacionActual) {
                is suma -> (operacionActual as suma).execute(primerNumero!!, segundoNumero)
                is resta -> (operacionActual as resta).execute(primerNumero!!, segundoNumero)
                is multiplicacion -> (operacionActual as multiplicacion).execute(primerNumero!!, segundoNumero)
                is division -> (operacionActual as division).execute(primerNumero!!, segundoNumero)
                else -> null
            }

            if (resultado != null) {
                pantalla.text = resultado.toString()
                numeroActual = resultado.toString()
                primerNumero = resultado
            }
        } catch (e: Exception) {
            pantalla.text = "Error, operación inválida"
        }

        esNuevaOperacion = true
    }

    // reiniciar la calculadora
    private fun reiniciarCalculadora() {
        numeroActual = ""
        primerNumero = null
        esNuevaOperacion = true
        pantalla.text = "0"
    }
}
