package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDICertificaciones
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import com.example.kotlin.robertoruizapp.data.network.model.CertificacionApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * Created by Dante Perez 2/11/2023
 * A class responsible for fetching certification data from a remote API.
 * It uses a service provided by a dependency injection module for network operations.
 *
 **/

class Certificaciones {
    // A service provided by a dependency injection module for network operations.
    // It is initialized lazily.
    private lateinit var api: CertificacionApiService

    /**
     * Fetches certification data from a remote API.
     * @return a CertificacionesObjeto object containing the certification data.
     */
    suspend fun getCertificaciones(): CertificacionesObjeto { // A suspending function.
        api = NetworkModuleDICertificaciones() // Initializes the api property.
        return api.getCertificaciones(LoginActivity.token) // Returns a CertificacionesObjeto object.
    }
}