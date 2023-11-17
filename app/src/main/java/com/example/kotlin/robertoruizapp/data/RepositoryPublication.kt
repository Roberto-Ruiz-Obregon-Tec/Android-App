package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIPublication
import com.example.kotlin.robertoruizapp.data.network.model.PublicationApiService
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicObjeto
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity


/**
 * Class that provides methods to interact with the Courses API and manage course fetching.
 */
class RepositoryPublication {
    private val api: PublicationApiService = NetworkModuleDIPublication()

    /**
     * Gets a course using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return PublicObjet that represents the obtained course, or null on error.
     */
    suspend fun getPublication(token: String): PublicObjeto? {
        return api.getPublication("Bearer ${LoginActivity.token}")
    }
}