package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import retrofit2.http.GET

interface ScholarshipApiService {
    // v1/certifications
    //http://localhost:3001/v1/scholarships
    @GET("scholarships")
    suspend fun getScholarship():
            BecasObjeto
}