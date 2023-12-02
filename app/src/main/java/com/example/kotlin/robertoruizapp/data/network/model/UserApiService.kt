package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.User.UsuarioObjeto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Defines the API service for interacting with user-related data.
 */
interface UserApiService {
    /**
     * Retrieves user data from the API.
     *
     * @param authToken The user's authentication token to access the API.
     * @return A [UsuarioObjeto] representing the user's data.
     */
    @GET("user")
    suspend fun getUser(@Header("Authorization") authToken: String): UsuarioObjeto

    /**
     * Retrieves a user by their ID from the server.
     *
     * @param userId The ID of the user to retrieve.
     * @param authToken The authentication token for the request.
     * @return A [Response] containing a [UsuarioObjeto] representing the user if the request is successful.
     */
    @GET("user/{id}")
    suspend fun getUserById(@Path("id") userId: String, @Header("Authorization") authToken: String): Response<UsuarioObjeto>
}