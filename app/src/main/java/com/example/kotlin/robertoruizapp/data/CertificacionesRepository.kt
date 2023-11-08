package com.example.kotlin.robertoruizapp.data

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDICertificaciones
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import com.example.kotlin.robertoruizapp.data.network.model.CertificacionApiService
import com.example.kotlin.robertoruizapp.data.network.model.CertificacionApiClient

/*
    * Created by Dante Perez 2/11/2023
    * Repository class for fetching certification data from a remote API.
    * This class abstract the logic of fetching data from the ViewModel.
    *
    * Parameters:
    *  api: A service provided by a dependency injection module for network operations.
    * It is initialized lazily.
    *
    * Methods:
    * getCertificaciones(): Fetches certification data from a remote API.
    * @return a CertificacionesObjeto object containing the certification data.
 */
class CertificacionesRepository {
    // A service provided by a dependency injection module for network operations.
    // It is initialized lazily.
    private lateinit var api: CertificacionApiService

    /**
     * Fetches certification data from a remote API.
     * @return a CertificacionesObjeto object containing the certification data.
     */
    suspend fun getCertificaciones(): CertificacionesObjeto? { // A suspending function.

        api = NetworkModuleDICertificaciones() // Initializes the api property.
        return api.getCertificaciones() // Returns a CertificacionesObjeto object.
    }
}