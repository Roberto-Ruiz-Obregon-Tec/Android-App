package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class CourseApiClient {
    private lateinit var api: CourseApiService

    suspend fun getCourse(token: String): CourseObject? {
        var result: CourseObject? = null
        try {
            return api.getCourse("Bearer $LoginActivity.token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            null
        }
        return result
    }
}