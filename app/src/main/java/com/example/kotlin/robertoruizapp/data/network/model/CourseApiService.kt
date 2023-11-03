package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import retrofit2.http.GET

interface CourseApiService {
    //http://localhost:3001/v1/course
    @GET("course")//URL Base
    suspend fun getCourse():
            CourseObject
}