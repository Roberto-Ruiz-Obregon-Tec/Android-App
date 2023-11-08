package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.ProgramAPIService
import com.example.kotlin.robertoruizapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    * Created by Dante Perez 2/11/2023
    * object NetworkModuleDICertificaciones: A singleton object representing a certification object.
    * Methods:
    * invoke(): A function that returns a CertificacionApiService object.
    * @return a CertificacionApiService object containing the certification data.
    *
    *
 */
object NetworkModuleDICertificaciones {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()
    private val okHttpClient: OkHttpClient = OkHttpClient()

    operator fun invoke(): CertificacionApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonFactory)
            .client(okHttpClient)
            .build()
            .create(CertificacionApiService::class.java)

    }

}
