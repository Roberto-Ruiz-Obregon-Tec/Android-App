package com.example.kotlin.robertoruizapp.data

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIMyCourses
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.data.network.model.MyCoursesApiClient
import com.example.kotlin.robertoruizapp.data.network.model.MyCoursesApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import retrofit2.http.GET

class MyCoursesRepository {
    private val api = MyCoursesApiClient()

    /**
     * Gets user courses using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return CourseObject that represents the obtained course, or null on error.
     */
    suspend fun getMyCourses(token: String): MyCourseObject? = api.getMyCourses("Bearer ${LoginActivity.token}")
}