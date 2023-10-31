package com.example.kotlin.robertoruizapp.data.network.model


import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import retrofit2.http.GET

interface companyCertificationApiService {
    //http://localhost:3001/v1/company-certifications
    @GET("company-certifications") //URL Bases
    suspend fun getCompanyCertification():
            CertificacionEmpresaObj
}