package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.User.UsuarioObjeto

/**
 * A client for interacting with the user API.
 */
class UserApiClient {
    private lateinit var api: UserApiService

    /**
     * Retrieves user data from the API.
     *
     * @param token The user's authentication token.
     * @return A [UsuarioObjeto] representing the user's data, or null if an error occurs.
     */
    suspend fun getUser(token: String): UsuarioObjeto? {
        try {
            return api.getUser("Bearer $token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            return null
        }
    }
}