package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIPublication
import com.example.kotlin.robertoruizapp.data.network.model.publication.Document
import com.example.kotlin.robertoruizapp.data.network.model.PublicationApiService
import com.example.kotlin.robertoruizapp.data.network.model.publication.CommentRequest
import com.example.kotlin.robertoruizapp.data.network.model.publication.LikeRequest
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicObjeto
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicationPostLike
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicationPostResponse
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

    suspend fun getPublicationId(publicationId: String, token: String): Document? {
        val response = api.getPublicationId(publicationId, "Bearer $token")
        // Verify that the response is successful and that the 'data' list contains at least one element
        return if (response.status == "success" && response.data.isNotEmpty()) {
            response.data.first()  // Returns the first Document in the list
        } else {
            null
        }
    }
    suspend fun createPublicationComment(token: String, commentRequest: CommentRequest): PublicationPostResponse? {
        return try {
            val response = api.creatPublicationComment("Bearer $token", commentRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
    suspend fun likePublication(token: String, likeRequest: LikeRequest): PublicationPostResponse? {
        return try {
            val response = api.likePublication("Bearer $token", likeRequest)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}