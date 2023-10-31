package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDIcompanyC
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.data.network.model.companyCertificationApiService

class companyCertificationRepository() {
    private lateinit var api: companyCertificationApiService

    suspend fun  getCompanyCertification():CertificacionEmpresaObj?{
        api = NetworkModuleDIcompanyC()
        return try{
            api.getCompanyCertification()

        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

}