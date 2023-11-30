package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDICourse
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService
import com.example.kotlin.robertoruizapp.data.network.model.Inscripcion.inscriptionRequest
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import retrofit2.http.GET


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

    /**
     * Retrieves a course by its ID.
     *
     * @param courseId The ID of the course to retrieve.
     * @param token The authentication token for the request.
     * @return A [Document] object representing the course if the request was successful and
     *         the 'data' field contains at least one element. Otherwise, it returns null.
     */
    suspend fun getCourseById(cursoId: String, token: String): Document? {
        val response = api.getCourseById(cursoId, "Bearer $token")
        // Verify that the response is successful and that the 'data' list contains at least one element
        return if (response.status == "success" && response.data.isNotEmpty()) {
            response.data.first()  // Returns the first UserDocument in the list
        } else {
            null
        }
    }

    suspend fun postInscription(courseId: String, token: String): String? {
        val body = inscriptionRequest(courseId = courseId)
        val response = api.postInscription( "Bearer $token", body)
        // Verify that the response is successful and that the 'data' list contains at least one element
        return if (response.isSuccessful ) {
            "Asistencia confirmada"
        } else {
            "Hubo un error"
        }
    }


}