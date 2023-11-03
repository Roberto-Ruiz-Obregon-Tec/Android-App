package com.example.kotlin.robertoruizapp.data

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDIcompanyC
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.data.network.model.companyCertificationApiClient
import com.example.kotlin.robertoruizapp.data.network.model.companyCertificationApiService

class companyCertificationRepository() {
    private lateinit var api: companyCertificationApiService

    suspend fun  getCompanyCertification():CertificacionEmpresaObj?{
        api = NetworkModuleDIcompanyC()
        return api.getCompanyCertification()


    }



}