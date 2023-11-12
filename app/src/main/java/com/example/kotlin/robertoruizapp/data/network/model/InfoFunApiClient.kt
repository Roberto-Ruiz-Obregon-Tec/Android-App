package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.InfoFoundation.InfoObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class InfoFunApiClient {
    private lateinit var api: InfoFunApiService

    /**
     * Gets a course using an authentication token.
     *
     * @param token Authentication code to make a request.
     * @return InfoObject Objet that represents the obtained course (NULL in case of error).
     */
    suspend fun getCourse(token: String): InfoObject? {
        var result: InfoObject? = null
        try {
            return api.getInfo("Bearer $LoginActivity.token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            null
        }
        return result
    }
}