package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

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
    // En tu CourseApiService
    @GET("course/{id}")
    suspend fun getCourseById(@Path("id") cursoId: String, @Header("Authorization") authToken: String): CourseObject

}