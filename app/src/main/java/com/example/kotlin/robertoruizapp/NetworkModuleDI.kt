package com.example.kotlin.robertoruizapp

import com.example.kotlin.robertoruizapp.Constants.BASE_URL_PROYECTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {

    //https://us-central1-robertoruiz-eca78.cloudfunctions.net/api/
//{{URL}}/v1/user/auth/signup
        fun getRetroInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(BASE_URL_PROYECTO)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
}
