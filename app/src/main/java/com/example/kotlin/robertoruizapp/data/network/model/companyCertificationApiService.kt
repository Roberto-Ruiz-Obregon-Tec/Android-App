package com.example.kotlin.robertoruizapp.data.network.model


import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import retrofit2.http.GET
import retrofit2.http.Header
/**
 *  In this part we call the Interface for the API service
 */
interface  companyCertificationApiService {
    //http://localhost:3001/v1/company-certifications
    /**
     *
     * Certification and company details are obtained
     * @param authToken is the token we receive which accepts the GET request
     * @return Returns an object which contains the details of the companies with certifications
     * @throws IOException If a network or server connection problem occurs.
     * @throws HttpException if the HTTP response was not successful
     */

    @GET("company-certifications") //URL Bases
    suspend fun getCompanyCertification(@Header("Authorizarion")authToken: String):
            CertificacionEmpresaObj
}