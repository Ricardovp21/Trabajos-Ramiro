package com.example.calculadora

class division {
    fun execute(firstNumber: Double, secondNumber: Double): Double {
        if (secondNumber == 0.0) {
            throw IllegalArgumentException("No se puede dividir por cero")
        }
        return firstNumber / secondNumber
    }
}
