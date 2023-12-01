package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.User.UsuarioObjeto

class UserApiClient {
    private lateinit var api: UserApiService

    suspend fun getUser(token: String): UsuarioObjeto? {
        try {
            return api.getUser("Bearer $token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            return null
        }
    }
}