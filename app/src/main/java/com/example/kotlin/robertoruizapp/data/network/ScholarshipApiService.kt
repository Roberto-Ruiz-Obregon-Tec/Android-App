package com.example.kotlin.robertoruizapp.data.network
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScholarshipApiService {
    @GET("scholarship")
    suspend fun getScholarshipList(
        @Query("limit") limit:Int
    ): BecasObjeto

    @GET("scholarship/{idScholarship}")
    suspend fun getScholarshipInfo(
        @Path("idScholarship") idScholarship:Int
    ) : BecasObjeto
}