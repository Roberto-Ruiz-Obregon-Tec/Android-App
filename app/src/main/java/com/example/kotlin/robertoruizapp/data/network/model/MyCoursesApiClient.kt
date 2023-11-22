package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject

class MyCoursesApiClient {
    private lateinit var api: MyCoursesApiService

    /**
     * Gets a user courses using an authentication token.
     *
     * @param token Authentication code to make a request.
     * @return MyCoursesObject Objet that represents the obtained course (NULL in case of error).
     */
    suspend fun getMyCourses(token: String): MyCourseObject? {
        try {
            return api.getMyCourses("Bearer $token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            return null
        }
    }
}