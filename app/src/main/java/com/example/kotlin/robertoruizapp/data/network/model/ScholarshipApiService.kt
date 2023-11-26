package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Scholarship api service
 *
 * @constructor Create empty Scholarship api service
 */
interface ScholarshipApiService {

    /**
     * Get scholarship method that connects de api and makes de get petition
     *
     * @param authToken help us to
     * @return
     */
    @GET("scholarships")
    suspend fun getScholarship(@Header("Authorization")authToken: String):
            BecasObjeto
}