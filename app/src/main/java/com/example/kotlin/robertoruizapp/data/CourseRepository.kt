package com.example.kotlin.robertoruizapp.data

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDICourse
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService

class CourseRepository {
    private lateinit var api:CourseApiService

    suspend fun getCourse(): CourseObject?{
        api = NetworkModuleDICourse()


        return api.getCourse()

    }
}