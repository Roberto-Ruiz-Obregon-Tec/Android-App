package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.data.network.model.Inscripcion.inscriptionRequest
import com.example.kotlin.robertoruizapp.data.network.model.Inscripcion.inscriptionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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

    /**
     * Retrieves a course by its ID from the server.
     *
     * @param courseId The ID of the course to retrieve.
     * @param authToken The authentication token for the request.
     * @return A [CourseObject] representing the course if the request is successful.
     */
    @GET("course/{id}")
    suspend fun getCourseById(@Path("id") cursoId: String, @Header("Authorization") authToken: String): CourseObject

    @POST("inscription/create")
    suspend fun postInscription(
        @Header("Authorization") authToken: String,
        @Body inscriptionRequest: inscriptionRequest
    ) : Response<inscriptionResponse>


}