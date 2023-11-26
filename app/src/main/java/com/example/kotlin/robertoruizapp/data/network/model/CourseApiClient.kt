package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * Class that makes requests to the Courses API and processes the responses.
 */
class CourseApiClient {
    private lateinit var api: CourseApiService

    /**
     * Gets a course using an authentication token.
     *
     * @param token Authentication code to make a request.
     * @return CourseObject Objet that represents the obtained course (NULL in case of error).
     */
    suspend fun getCourse(token: String): CourseObject? {
        try {
            return api.getCourse("Bearer $token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            return null
        }
    }
}