package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * The client accesses the Company certifications API
 * Handles communication and possible exceptions
 */
class companyCertificationApiClient {

    /**
     * Make the request to the API service to obtain the characteristics of the Company certification
     * Use the LoginActivity Token to authenticate
     *
     * @return an object that has the certification details or 'null' if there is an exception
     */

    private lateinit var api: companyCertificationApiService

    suspend fun getCompanyCertification(): CertificacionEmpresaObj?{
        var result: CertificacionEmpresaObj? = null
        Log.d("TRY", "TRY")
        try {
            return api.getCompanyCertification("Bearer ${LoginActivity.token}")

        } catch (e: java.lang.Exception) {
            Log.d("Catch","Holaaa" + {result})
            null
        }
        return result
    }
}