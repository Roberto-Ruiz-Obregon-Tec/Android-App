package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.publication.CommentRequest
import com.example.kotlin.robertoruizapp.data.network.model.publication.LikeRequest
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicObjeto
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicationPostLike
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicationPostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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

    @POST("publication/comment/create")
    suspend fun creatPublicationComment(@Header("Authorization") authToken: String, @Body commentRequest: CommentRequest
    ): Response<PublicationPostResponse>

    @POST("publication/like")
    suspend fun likePublication(@Header("Authorization") authToken: String, @Body likeRequest: LikeRequest): Response<PublicationPostResponse>

}