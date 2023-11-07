package com.example.kotlin.robertoruizapp.data.network

import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDICourse {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    // Crear una instancia de OkHttpClient con un interceptor que añade el token JWT.
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val originalRequest = chain.request()

            // Asegúrate de que el token esté presente antes de intentar añadirlo a la cabecera
            val token = LoginActivity.token
            if (token.isNotEmpty()) {
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            } else {
                chain.proceed(originalRequest)
            }
        }
    }.build()

    operator fun invoke(): CourseApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(CourseApiService::class.java)
    }
}
