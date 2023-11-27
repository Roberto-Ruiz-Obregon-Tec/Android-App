package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import retrofit2.http.GET
import retrofit2.http.Header

interface MyCoursesApiService {
    /**
     * Gets a course using an authentication token.
     *
     * @param authToken Authentication token for the request.
     * @return MyCoursesObject that represents the returned courses.
     */
    @GET("user/mycourses")
    suspend fun getMyCourses(@Header("Authorization") authToken: String): Document
}