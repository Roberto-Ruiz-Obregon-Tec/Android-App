package com.example.kotlin.robertoruizapp.data

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDICourse
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class CourseRepository {
    private val api: CourseApiService = NetworkModuleDICourse()

    suspend fun getCourse(token: String): CourseObject? {
        return api.getCourse("Bearer ${LoginActivity.token}")
    }
}