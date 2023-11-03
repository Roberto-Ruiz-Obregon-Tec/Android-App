package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject

class CourseApiClient {
    private lateinit var api: CourseApiService
    suspend fun getCourse(): CourseObject?{
        var result: CourseObject? = null
        try{
            result =api.getCourse()
        }catch(e: java.lang.Exception){
            Log.d("Catch", "Result: ${result}")
            e.printStackTrace()
            null
        }
        return result
    }
}