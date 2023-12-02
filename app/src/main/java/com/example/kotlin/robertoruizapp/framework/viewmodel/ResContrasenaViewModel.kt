package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class ResContrasenaViewModel : ViewModel() {

    // Simula la verificación del correo electrónico (esto debería hacerse en el backend)
    fun checkEmail(email: String) = liveData(Dispatchers.IO) {
        try {
            // Simula un retraso de red
            delay(2000)

            // Simula una verificación de correo electrónico
            val isEmailRegistered = checkEmailInDatabase(email) // Reemplazar con la lógica real de verificación

            // Emite el resultado
            emit(isEmailRegistered)
        } catch (e: Exception) {
            emit(false)
        }
    }

    // Simula una función de verificación de correo electrónico en la base de datos
    private fun checkEmailInDatabase(email: String): Boolean {
        // Aquí deberías consultar la base de datos real
        // Por ahora, simplemente devuelve falso o verdadero aleatoriamente para la simulación
        return listOf(true, false).random()
    }
}
