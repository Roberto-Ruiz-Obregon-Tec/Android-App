package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDICourse
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity


/**
 * Class that provides methods to interact with the Courses API and manage course fetching.
 */
class CourseRepository {
    private val api: CourseApiService = NetworkModuleDICourse()

    /**
     * Gets a course using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return CourseObject that represents the obtained course, or null on error.
     */
    suspend fun getCourse(token: String): CourseObject? {
        return api.getCourse("Bearer ${LoginActivity.token}")
    }
}