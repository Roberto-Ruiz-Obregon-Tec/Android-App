package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import retrofit2.http.GET

interface CertificacionApiService {
    // v1/certifications
    //http://localhost:3001/v1/certifications
    @GET("certifications")
    suspend fun getCertificaciones():
            CertificacionesObjeto

}