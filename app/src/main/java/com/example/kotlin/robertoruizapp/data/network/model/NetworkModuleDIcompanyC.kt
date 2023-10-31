package com.example.kotlin.robertoruizapp.data.network.model


import com.example.kotlin.robertoruizapp.data.network.ProgramAPIService
import com.example.kotlin.robertoruizapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDIcompanyC {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()
    private val okHttpClient: OkHttpClient = OkHttpClient()

    /**
     * adds the configuration of Retrofit to the interface that represents this module
     *
     * @return [ProgramAPIService] configured
     */
    operator fun invoke(): companyCertificationApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(companyCertificationApiService::class.java)
    }
}