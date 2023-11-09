package com.example.kotlin.robertoruizapp.data

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDIcompanyC
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.data.network.model.companyCertificationApiClient
import com.example.kotlin.robertoruizapp.data.network.model.companyCertificationApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class companyCertificationRepository() {
    private val api: companyCertificationApiService = NetworkModuleDIcompanyC()


    /**
     * Uses the authentication token, which is provided by [LoginActivity]
     *
     * @param authentication token, although it is received as a parameter, the one stored aesthetically is used
     *
     * @return returns an object [CertificacionEmpresaObj] with the details of the certifications and companies
     */
    suspend fun  getCompanyCertification(token: String):CertificacionEmpresaObj?{
        return api.getCompanyCertification("Bearer ${LoginActivity.token}")

    }


}