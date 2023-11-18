package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicObjeto
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * Class that makes requests to the Publication API and processes the responses.
 */
class PublicationApiClient {
    private lateinit var api: PublicationApiService

    /**
     * Gets a course using an authentication token.
     *
     * @param token Authentication code to make a request.
     * @return publicObjeto Objet that represents the obtained course (NULL in case of error).
     */
    suspend fun getPublication(token: String): PublicObjeto? {
        var result: PublicObjeto? = null
        try {
            return api.getPublication("Bearer $LoginActivity.token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            null
        }
        return result
    }
}