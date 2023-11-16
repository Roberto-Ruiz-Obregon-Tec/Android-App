package com.example.kotlin.robertoruizapp.data.network.model


import com.example.kotlin.robertoruizapp.data.network.model.InfoFoundation.InfoObject
import retrofit2.http.GET
import retrofit2.http.Header

interface InfoFunApiService {

    /**
     * Gets a course using an authentication token.
     *
     * @param authToken Authentication token for the request.
     * @return CourseObject that represents the returned course.
     */
    @GET("informacion-fundacion")
    suspend fun getInfo(@Header("Authorization") authToken: String): InfoObject
}