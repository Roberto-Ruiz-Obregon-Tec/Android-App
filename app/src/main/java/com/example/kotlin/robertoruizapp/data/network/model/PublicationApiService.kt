package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicObjeto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Interface that defines how requests to the Courses API should be made.
 */
interface PublicationApiService {
    /**
     * Gets a course using an authentication token.
     *
     * @param authToken Authentication token for the request.
     * @return PublicObjeto that represents the returned course.
     */
    @GET("publication")
    suspend fun getPublication(@Header("Authorization") authToken: String):
            PublicObjeto

    @GET("publication/{id}")
    suspend fun getPublicationId(@Path("id") publicationId: String, @Header("Authorization") authToken: String):
            PublicObjeto
}