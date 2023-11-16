package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Interface that defines how requests to the Courses API should be made.
 */
interface CourseApiService {
    /**
     * Gets a course using an authentication token.
     *
     * @param authToken Authentication token for the request.
     * @return CourseObject that represents the returned course.
     */
    @GET("course")
    suspend fun getCourse(@Header("Authorization") authToken: String): CourseObject
}