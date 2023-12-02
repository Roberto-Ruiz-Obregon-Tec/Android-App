package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIForgotPassword
import com.example.kotlin.robertoruizapp.data.network.ForgotPasswordRequest
import kotlinx.coroutines.Dispatchers

class ForgotPasswordViewModel : ViewModel() {
    // Obtener directamente la instancia del servicio
    private val apiService = NetworkModuleDIForgotPassword.provideForgotPasswordService()
    fun forgotPassword(email: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = apiService.forgotPassword(ForgotPasswordRequest(email))
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                // Loguear el código de estado y la respuesta de error para diagnóstico
                val errorBody = response.errorBody()?.string()
                emit(Resource.Error("Error al reestablecer: Código ${response.code()} - $errorBody", null))
            }

        } catch (e: Exception) {
            emit(Resource.Error("Exception: ${e.localizedMessage}", null))
        }
    }
}

// Clase de ayuda para manejar los estados de la respuesta (Cargando, Éxito, Error)
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
