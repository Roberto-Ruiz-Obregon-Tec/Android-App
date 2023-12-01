package com.example.kotlin.robertoruizapp.data.network

import com.example.kotlin.robertoruizapp.data.network.model.BankApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDIBank {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val originalRequest = chain.request()

            // Make sure the token is present before trying to add it to the header
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

    operator fun invoke(): BankApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(BankApiService::class.java)
    }
}