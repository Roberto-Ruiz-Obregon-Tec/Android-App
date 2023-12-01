package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.User.UsuarioObjeto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserApiService {
    @GET("user")
    suspend fun getUser(@Header("Authorization") authToken: String): UsuarioObjeto

    /**
     * Retrieves a course by its ID from the server.
     *
     * @param userId The ID of the user to retrieve.
     * @param authToken The authentication token for the request.
     * @return A [UsuarioObjeto] representing the user if the request is successful.
     */
    @GET("user/{id}")
    suspend fun getUserById(@Path("id") userId: String, @Header("Authorization") authToken: String): Response<UsuarioObjeto>
}