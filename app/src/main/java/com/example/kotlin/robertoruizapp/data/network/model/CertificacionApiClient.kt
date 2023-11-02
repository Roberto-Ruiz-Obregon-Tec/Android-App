package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto


class CertificacionApiClient {

    private lateinit var api: CertificacionApiService

    suspend fun getCertificaciones(): CertificacionesObjeto? {
        api = NetworkModuleDICertificaciones()
        Log.d("Try", "Try")
        var result: CertificacionesObjeto? = null

        try {
            result = api.getCertificaciones()
        } catch (e: java.lang.Exception) {
            Log.d("Catch", "Holaa" + { result })
            e.printStackTrace()
            null

        }
        return result
    }
}