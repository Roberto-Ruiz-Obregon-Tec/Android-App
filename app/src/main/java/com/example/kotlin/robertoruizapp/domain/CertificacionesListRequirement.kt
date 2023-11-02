package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import com.example.kotlin.robertoruizapp.data.CertificacionesRepository

class CertificacionesListRequirement {

    private val certificacionesRepository = CertificacionesRepository()

    suspend operator fun invoke(): CertificacionesObjeto? = certificacionesRepository.getCertificaciones()
}