package com.example.kotlin.robertoruizapp.data

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIUser
import com.example.kotlin.robertoruizapp.data.network.model.User.UserDocument
import com.example.kotlin.robertoruizapp.data.network.model.User.UsuarioObjeto
import com.example.kotlin.robertoruizapp.data.network.model.UserApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class UserRepository {
    private val api: UserApiService = NetworkModuleDIUser()

    /**
     * Gets a user using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return UsuarioObjeto that represents the obtained user, or null on error.
     */
    suspend fun getUser(token: String): UsuarioObjeto? {
        return api.getUser("Bearer ${LoginActivity.token}")
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param userId The ID of the course to retrieve.
     * @param token The authentication token for the request.
     * @return A [UserDocument] object representing the user if the request was successful and
     *         the 'data' field contains at least one element. Otherwise, it returns null.
     */
    suspend fun getUserById(userId: String, token: String): UserDocument? {
        val response = api.getUserById(userId, "Bearer $token")
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.data.document
        } else {
            null
        }
    }
}