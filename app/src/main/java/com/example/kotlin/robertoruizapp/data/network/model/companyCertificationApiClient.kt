package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj

class companyCertificationApiClient {
    private lateinit var api: companyCertificationApiService

    suspend fun getCompanyCertification(): CertificacionEmpresaObj?{
        api = NetworkModuleDIcompanyC()
        Log.d("TRY", "TRY")
        var result: CertificacionEmpresaObj? = null

        try {
            result = api.getCompanyCertification()

        } catch (e: java.lang.Exception) {
            Log.d("Catch","Holaaa" + {result})
            e.printStackTrace()
            null
        }
        return result
    }
}