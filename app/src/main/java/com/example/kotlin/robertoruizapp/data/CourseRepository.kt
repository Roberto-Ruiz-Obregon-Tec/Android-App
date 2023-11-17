package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDICourse
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService
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

    suspend fun getCourseById(cursoId: String, token: String): Document? {
        val response = api.getCourseById(cursoId, "Bearer $token")
        // Verifica que la respuesta es exitosa y que la lista 'data' contiene al menos un elemento
        return if (response.status == "success" && response.data.isNotEmpty()) {
            response.data.first()  // Retorna el primer Documento de la lista
        } else {
            null
        }
    }


}