package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import retrofit2.http.GET

/*
    * Created by Dante Perez 2/11/2023
    * An interface representing a certification object.
    *
    * Methods:
    * @GET("certifications"): A GET request to the certifications endpoint.
    * suspend fun getCertificaciones(): A suspending function that returns a CertificacionesObjeto object.
    * @return a CertificacionesObjeto object containing the certification data.
    *
 */
interface CertificacionApiService {
    // v1/certifications
    //http://localhost:3001/v1/certifications
    @GET("certifications")
    suspend fun getCertificaciones():
            CertificacionesObjeto

}