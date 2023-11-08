package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto

/*
    * Created by Dante Perez 2/11/2023
    * CertificacionApiClient class for fetching certification data from a remote API.
    * This class abstract the logic of fetching data from the ViewModel.
    *
    * Parameters:
    * api: A service provided by a dependency injection module for network operations.
    * It is initialized lazily.
    *
    * Methods:
    * getCertificaciones(): Fetches certification data from a remote API.
    * @return a CertificacionesObjeto object containing the certification data.
    *
 */
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