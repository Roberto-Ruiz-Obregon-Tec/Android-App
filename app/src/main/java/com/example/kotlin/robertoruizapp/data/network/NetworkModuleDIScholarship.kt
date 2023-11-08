package com.example.kotlin.robertoruizapp.data.network

import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService
import com.example.kotlin.robertoruizapp.data.network.model.ScholarshipApiService
import com.example.kotlin.robertoruizapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDIScholarship {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()
    private val okHttpClient: OkHttpClient = OkHttpClient()
    operator fun invoke(): ScholarshipApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(ScholarshipApiService::class.java)
    }
}