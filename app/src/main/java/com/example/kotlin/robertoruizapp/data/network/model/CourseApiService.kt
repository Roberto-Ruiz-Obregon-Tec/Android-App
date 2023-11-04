package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import retrofit2.http.GET
import retrofit2.http.Header

interface CourseApiService {
    @GET("course")
    suspend fun getCourse(@Header("Authorization") authToken: String): CourseObject
}