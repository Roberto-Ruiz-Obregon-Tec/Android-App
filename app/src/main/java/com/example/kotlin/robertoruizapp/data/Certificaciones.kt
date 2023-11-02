package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDICertificaciones
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import com.example.kotlin.robertoruizapp.data.network.model.CertificacionApiService
import com.example.kotlin.robertoruizapp.data.network.model.CertificacionApiClient

class Certificaciones {
    private lateinit var api: CertificacionApiService

    suspend fun getCertificaciones(): CertificacionesObjeto {
        api = NetworkModuleDICertificaciones()
        return api.getCertificaciones()
    }
}